data "aws_lb" "load_balancer" {
    name = "healthmed-load-balancer"
}

resource "aws_api_gateway_vpc_link" "vpc_link" {
    name        = "heath-med-api"
    target_arns = [data.aws_lb.load_balancer.arn]
}

resource "aws_api_gateway_rest_api" "api_gateway" {
    name = "HeathMed API"

    body = templatefile(
        "${path.module}/../openapi.json",
        {
            target_group_port          = 80
            dns_name                   = data.aws_lb.load_balancer.dns_name
            vpc_link_id                = aws_api_gateway_vpc_link.vpc_link.id
            api_gateway_role           = aws_iam_role.api_gateway_lambda.arn
        }
    )

    endpoint_configuration {
        types = ["REGIONAL"]
    }
}

data "aws_iam_policy_document" "api_gateway" {
    statement {
        effect = "Allow"

        principals {
            type        = "*"
            identifiers = ["*"]
        }

        actions   = ["execute-api:Invoke"]
        resources = ["${aws_api_gateway_rest_api.api_gateway.execution_arn}/*/*/*"]
    }
}

resource "aws_api_gateway_rest_api_policy" "api_gateway" {
    rest_api_id = aws_api_gateway_rest_api.api_gateway.id
    policy      = data.aws_iam_policy_document.api_gateway.json
}

resource "aws_api_gateway_deployment" "api_gateway" {
    rest_api_id = aws_api_gateway_rest_api.api_gateway.id

    triggers = {
        redeployment = sha1(jsonencode(aws_api_gateway_rest_api.api_gateway.body))
    }

    lifecycle {
        create_before_destroy = true
    }
}

resource "aws_api_gateway_stage" "api_gateway" {
    stage_name    = "live"
    deployment_id = aws_api_gateway_deployment.api_gateway.id
    rest_api_id   = aws_api_gateway_rest_api.api_gateway.id
}
