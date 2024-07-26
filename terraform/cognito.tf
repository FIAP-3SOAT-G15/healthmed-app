resource "aws_cognito_user_pool" "user_pool" {
    name = "healthmed"

    admin_create_user_config {
        allow_admin_create_user_only = true
    }

    schema {
        attribute_data_type = "String"
        name                = "CRM"
        required            = false
    }

    schema {
        attribute_data_type = "String"
        name                = "CPF"
        required            = false

        string_attribute_constraints {
            min_length = 11
            max_length = 11
        }
    }

    schema {
        attribute_data_type = "String"
        name                = "CUSTOMER_ID"
        required            = false
    }

    lifecycle {
        ignore_changes = [
            schema
        ]
    }

}

resource "aws_cognito_user_group" "doctor" {
    name         = "doctor"
    user_pool_id = aws_cognito_user_pool.user_pool.id
}

resource "aws_cognito_user_group" "patient" {
    name         = "patient"
    user_pool_id = aws_cognito_user_pool.user_pool.id
}

resource "aws_cognito_user_pool_client" "client" {
    name         = "client"
    user_pool_id = aws_cognito_user_pool.user_pool.id
}

output "user_pool_id" {
    value = aws_cognito_user_pool.user_pool.id
}
