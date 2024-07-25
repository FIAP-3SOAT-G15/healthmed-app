package com.fiap.healthmed.driver.web.request

import io.swagger.v3.oas.annotations.media.Schema

data class JustificationCancellationRequest(
    @Schema(description = "Justification text", example = "I'm not feeling well")
    val text: String
)
