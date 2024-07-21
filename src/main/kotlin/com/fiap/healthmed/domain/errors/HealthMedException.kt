package com.fiap.healthmed.domain.errors

data class HealthMedException(
    var errorType: ErrorType,
    override val cause: Throwable? = null,
    override val message: String?
) : RuntimeException(message, cause)