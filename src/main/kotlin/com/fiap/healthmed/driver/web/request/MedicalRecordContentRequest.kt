package com.fiap.healthmed.driver.web.request

import io.swagger.v3.oas.annotations.media.Schema

data class MedicalRecordContentRequest(
    @Schema(description = "Medical record content", example = "Patient has a fever")
    val content: String
)
