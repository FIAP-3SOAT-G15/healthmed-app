terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = ">= 5.36.0"
    }
  }

  backend "s3" {
    bucket = "fiap-3soat-g15-iac-healthmed"
    key    = "live/terraform.tfstate"
    region = "us-east-1"
  }
}

data "aws_caller_identity" "current" {}

provider "aws" {
  region = var.region
}

provider "kubernetes" {
    host                   = module.eks.cluster_endpoint
    cluster_ca_certificate = base64decode(module.eks.cluster_certificate_authority_data)

    exec {
        api_version = "client.authentication.k8s.io/v1beta1"
        command     = "aws"
        args        = ["eks", "get-token", "--cluster-name", module.eks.cluster_name]
    }
}

provider "helm" {
    kubernetes {
        host                   = module.eks.cluster_endpoint
        cluster_ca_certificate = base64decode(module.eks.cluster_certificate_authority_data)

        exec {
            api_version = "client.authentication.k8s.io/v1beta1"
            command     = "aws"
            args        = ["eks", "get-token", "--cluster-name", module.eks.cluster_name]
        }
    }
}
