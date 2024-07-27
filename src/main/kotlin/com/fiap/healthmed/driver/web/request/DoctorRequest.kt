package com.fiap.healthmed.driver.web.request

import com.fiap.healthmed.domain.Doctor
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class DoctorRequest(
    @Schema(description = "CRM", example = "123456")
    val crm: String,
    @Schema(description = "CPF", example = "28685216907")
    val document: String,
    @Schema(description = "Specialty", example = "Cardiologist")
    val specialty: String,
    @Schema(description = "Name", example = "John Doe")
    val name: String,
    @Schema(description = "Email", example = "john_doe@gmail.com")
    val email: String,
    @Schema(description = "Phone number", example = "+5511999999999")
    val phoneNumber: String,
    @Schema(description = "Service ZIP code", example = "12345678")
    val serviceZipCode: String,
    @Schema(description = "Service address", example = "123, Main St.")
    val serviceAddress: String,
    @Schema(description = "Available times")
    val availableTimes: AvailableTimesRequest,
    @Schema(description = "Appointment price", example = "100.00")
    val appointmentPrice: BigDecimal,
    @Schema(description = "Password", example = "123456")
    val password: String,
)

fun DoctorRequest.toDomain(): Doctor = Doctor(
    crm = crm,
    document = document,
    specialty = specialty,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    serviceZipCode = serviceZipCode,
    serviceAddress = serviceAddress,
    availableTimes = availableTimes.toDomain(),
    appointmentPrice = appointmentPrice,
)
