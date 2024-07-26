resource "aws_s3_bucket" "medical_record_bucket" {
    bucket = var.medical_record_bucket_name
    tags   = var.tags
}

resource "aws_s3_bucket" "website_bucket" {
  bucket = var.website_bucket_name
  tags   = var.tags
}

resource "aws_s3_bucket_website_configuration" "website_configuration" {
  bucket = aws_s3_bucket.website_bucket.id

  index_document {
    suffix = "index.html"
  }

  error_document {
    key = "404.html"
  }
}

resource "aws_s3_bucket_public_access_block" "website_bucket_public_access_block" {
  bucket = aws_s3_bucket.website_bucket.id

  block_public_acls       = false
  block_public_policy     = false
  ignore_public_acls      = false
  restrict_public_buckets = false
}

resource "aws_s3_bucket_ownership_controls" "website_bucket_ownership_controls" {
  bucket = aws_s3_bucket.website_bucket.id
  rule {
    object_ownership = "BucketOwnerPreferred"
  }
  depends_on = [aws_s3_bucket_public_access_block.website_bucket_public_access_block]
}

resource "aws_s3_bucket_ownership_controls" "medical_record_ownership_controls" {
    bucket = aws_s3_bucket.medical_record_bucket.id
    rule {
        object_ownership = "BucketOwnerPreferred"
    }
}

resource "aws_s3_bucket_server_side_encryption_configuration" "medical_record_sse_config" {
    bucket = aws_s3_bucket.medical_record_bucket.id

    rule {
        apply_server_side_encryption_by_default {
            kms_master_key_id = aws_kms_key.medical_record_bucket_kms_key.arn
            sse_algorithm     = "aws:kms"
        }

        bucket_key_enabled = true
    }
}

resource "aws_s3_bucket_acl" "website_bucket_acl" {
  bucket     = aws_s3_bucket.website_bucket.id
  acl        = "public-read"
  depends_on = [aws_s3_bucket_ownership_controls.website_bucket_ownership_controls]
}

resource "aws_s3_bucket_acl" "medical_record_bucket_acl" {
    bucket     = aws_s3_bucket.medical_record_bucket.id
    acl        = "private"
    depends_on = [aws_s3_bucket_ownership_controls.medical_record_ownership_controls]
}

resource "aws_s3_bucket_policy" "website_bucket_policy" {
  bucket = aws_s3_bucket.website_bucket.id

  policy = data.aws_iam_policy_document.website_bucket_policy_document.json
}

data "aws_iam_policy_document" "website_bucket_policy_document" {
  statement {
    sid = "PublicReadGetObject"

    effect = "Allow"

    principals {
      type        = "*"
      identifiers = ["*"]
    }

    actions = [
      "s3:GetObject",
    ]

    resources = [
      "arn:aws:s3:::${var.website_bucket_name}/*"
    ]
  }
}
