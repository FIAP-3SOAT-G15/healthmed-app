package com.fiap.healthmed.driver.web.request

import com.fiap.healthmed.domain.Patient
import io.swagger.v3.oas.annotations.media.Schema

data class PatientRequest(
    @Schema(description = "CPF", example = "12345678900")
    val document: String,
    @Schema(description = "Name", example = "Foo Bar")
    val name: String,
    @Schema(description = "Email", example = "foo_bar@gmail.com")
    val email: String,
    @Schema(description = "Phone number", example = "+5511999999999")
    val phoneNumber: String,
    @Schema(description = "ZIP code", example = "12345678")
    val zipCode: String,
    @Schema(description = "Address", example = "123, Main St.")
    val address: String,
    @Schema(description = "Password", example = "123456")
    val password: String,
)

fun PatientRequest.toDomain(): Patient = Patient(
    document = document,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    zipCode = zipCode,
    address = address
)
