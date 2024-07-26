locals {
    clustername        = "healthmed"
    cluster_version    = "1.29"
    app_namespace      = "healthmed"
}

// https://github.com/terraform-aws-modules/terraform-aws-eks
module "eks" {
    source  = "terraform-aws-modules/eks/aws"
    version = "20.8.3"

    cluster_name                    = local.clustername
    cluster_version                 = local.cluster_version
    cluster_endpoint_private_access = true
    cluster_endpoint_public_access  = true

    access_entries = {
        root_admin = {
            kubernetes_groups = []
            principal_arn     = "arn:aws:iam::${data.aws_caller_identity.current.account_id}:root"

            policy_associations = {
                admin = {
                    policy_arn = "arn:aws:eks::aws:cluster-access-policy/AmazonEKSClusterAdminPolicy"
                    access_scope = {
                        type = "cluster"
                    }
                }
            }
        }
    }

    enable_cluster_creator_admin_permissions = true

    vpc_id     = module.vpc.vpc_id
    subnet_ids = module.vpc.private_subnets

    eks_managed_node_group_defaults = {
        disk_size      = 20
        instance_types = ["t3.small"]
    }

    eks_managed_node_groups = {
        default_node_group = {
            use_custom_launch_template = false

            desired_size = 2
            min_size     = 1
            max_size     = 5

            capacity_type = "SPOT"
        }
    }

    cluster_addons = {
        // Service Discovery
        coredns = {
            most_recent = true
        }
    }
}

# NAMESPACES

# Creating as Terraform resource (instead of Kubernetes manifest)
# for removing Kubernetes services (like load balancers) when destroying it
resource "kubernetes_namespace" "healthmed_namespace" {
    metadata {
        name = local.app_namespace
        annotations = {
            name = local.app_namespace
        }
    }
}

# ADD-ONS

# Install AWS Secrets and Configuration Provider (ASCP)
# https://docs.aws.amazon.com/secretsmanager/latest/userguide/integrating_csi_driver.html
resource "helm_release" "csi_secrets_store" {
    name       = "csi-secrets-store"
    repository = "https://kubernetes-sigs.github.io/secrets-store-csi-driver/charts"
    chart      = "secrets-store-csi-driver"
    namespace  = "kube-system"

    set {
        name  = "syncSecret.enabled"
        value = "true"
    }

    set {
        name  = "enableSecretRotation"
        value = "true"
    }

    depends_on = [
        module.eks
    ]
}

resource "helm_release" "secrets_provider_aws" {
    name       = "secrets-provider-aws"
    repository = "https://aws.github.io/secrets-store-csi-driver-provider-aws"
    chart      = "secrets-store-csi-driver-provider-aws"
    namespace  = "kube-system"

    depends_on = [
        module.eks,
        helm_release.csi_secrets_store
    ]
}

# Install AWS Load Balancer Controller
# https://docs.aws.amazon.com/eks/latest/userguide/aws-load-balancer-controller.html
# https://github.com/aws/eks-charts/tree/master/stable/aws-load-balancer-controller
resource "helm_release" "aws_load_balancer_controller" {
    name       = "aws-load-balancer-controller"
    repository = "https://aws.github.io/eks-charts"
    chart      = "aws-load-balancer-controller"
    namespace  = "kube-system"

    set {
        name  = "region"
        value = var.region
    }

    set {
        name  = "vpcId"
        value = module.vpc.vpc_id
    }

    set {
        name  = "clusterName"
        value = local.clustername
    }

    set {
        name  = "serviceAccount.create"
        value = false
    }

    set {
        name  = "serviceAccount.name"
        value = "kube-system-service-account"
    }
}

# ROLES ATTACHED TO SERVICE ACCOUNTS

module "kube_system_service_account_role" {
    source    = "terraform-aws-modules/iam/aws//modules/iam-role-for-service-accounts-eks"
    role_name = "HealthMedKubeSystemServiceAccount"

    oidc_providers = {
        main = {
            provider_arn = module.eks.oidc_provider_arn
            namespace_service_accounts = [
                "kube-system:kube-system-service-account",
            ]
        }
    }

    attach_load_balancer_controller_policy = true

    tags = var.tags
}

module "app_service_account_role" {
    source    = "terraform-aws-modules/iam/aws//modules/iam-role-for-service-accounts-eks"
    role_name = "HealthMedServiceAccount"

    oidc_providers = {
        main = {
            provider_arn = module.eks.oidc_provider_arn
            namespace_service_accounts = [
                "${local.app_namespace}:${local.app_namespace}-service-account",
            ]
        }
    }

    role_policy_arns = {
        HealthMedRDSSecretsReadOnlyPolicy = aws_iam_policy.rds_secrets_read_only_policy.arn
        HealthMedRDSParamsReadOnlyPolicy  = aws_iam_policy.rds_params_read_only_policy.arn
    }

    tags = var.tags
}

# SERVICE ACCOUNTS

resource "kubernetes_service_account" "kube_system_service_account" {
    metadata {
        name      = "kube-system-service-account"
        namespace = "kube-system"
        annotations = {
            "eks.amazonaws.com/role-arn" = module.kube_system_service_account_role.iam_role_arn
        }
    }
}

resource "kubernetes_service_account" "app_service_account" {
    metadata {
        name      = "${local.app_namespace}-service-account"
        namespace = local.app_namespace
        annotations = {
            "eks.amazonaws.com/role-arn" = module.app_service_account_role.iam_role_arn
        }
    }
}
