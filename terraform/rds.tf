locals {
    dbname   = "healthmeddb"
    username = "master"
    port     = 5432
}

module "db" {
    source  = "terraform-aws-modules/rds/aws"
    version = "6.5.2"

    identifier = local.dbname

    engine               = "postgres"
    engine_version       = "15"
    family               = "postgres15"
    major_engine_version = "15"
    instance_class       = "db.t3.micro"

    allocated_storage     = 10
    max_allocated_storage = 20

    storage_encrypted = false

    db_name  = local.dbname
    username = local.username
    port     = local.port

    db_subnet_group_name   = module.vpc.database_subnet_group_name
    vpc_security_group_ids = [module.security_group.security_group_id]

    backup_retention_period = 0
    skip_final_snapshot     = true
    deletion_protection     = false
}

module "security_group" {
    source  = "terraform-aws-modules/security-group/aws"
    version = "~> 5.0"

    name   = local.dbname
    vpc_id = module.vpc.vpc_id

    ingress_with_cidr_blocks = [
        {
            from_port   = local.port
            to_port     = local.port
            protocol    = "tcp"
            cidr_blocks = module.vpc.vpc_cidr_block
        },
    ]
}

module "rds_params" {
    source = "terraform-aws-modules/ssm-parameter/aws"
    name   = "/live/healthmed/db"
    type   = "String"

    value = jsonencode({
        name : local.dbname,
        endpoint : module.db.db_instance_endpoint,
        port : local.port
    })
}

resource "aws_iam_policy" "rds_secrets_read_only_policy" {
    name = "HealthmedRDSSecretsReadOnlyPolicy"

    policy = jsonencode({
        Version = "2012-10-17"
        Statement = [
            {
                Effect = "Allow"
                Action = [
                    "secretsmanager:DescribeSecret",
                    "secretsmanager:GetSecretValue"
                ],
                Resource = module.db.db_instance_master_user_secret_arn
            }
        ]
    })
}

resource "aws_iam_policy" "rds_params_read_only_policy" {
    name = "HealthmedRDSParamsReadOnlyPolicy"

    policy = jsonencode({
        Version = "2012-10-17"
        Statement = [
            {
                Effect = "Allow"
                Action = [
                    "ssm:GetParameter",
                    "ssm:GetParameters"
                ],
                Resource = module.rds_params.ssm_parameter_arn
            }
        ]
    })
}
