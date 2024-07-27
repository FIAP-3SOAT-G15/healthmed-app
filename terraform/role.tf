data "aws_iam_policy_document" "api_gateway_lambda_policy" {
    statement {
        effect    = "Allow"
        actions   = ["lambda:InvokeFunction"]
        resources = ["*"]
    }
}

resource "aws_iam_role_policy" "api_gateway_lambda" {
    name = "api_gateway_lambda_policy"
    role = aws_iam_role.api_gateway_lambda.id

    policy = data.aws_iam_policy_document.api_gateway_lambda_policy.json

}

data "aws_iam_policy_document" "api_gateway_lambda_trust" {
    statement {
        effect = "Allow"

        principals {
            type        = "Service"
            identifiers = ["apigateway.amazonaws.com"]
        }

        actions = ["sts:AssumeRole"]
    }
}

resource "aws_iam_role" "api_gateway_lambda" {
    name               = "api_gateway_lambda_role"
    assume_role_policy = data.aws_iam_policy_document.api_gateway_lambda_trust.json
}
