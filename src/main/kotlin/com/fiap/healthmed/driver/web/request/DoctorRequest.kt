package com.fiap.healthmed.driver.web.request

import com.fiap.healthmed.domain.Doctor
import java.math.BigDecimal

data class DoctorRequest(
    val crm: String,
    val document: String,
    val specialty: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val serviceZipCode: String,
    val serviceAddress: String,
    val availableTimes: AvailableTimesRequest,
    val appointmentPrice: BigDecimal,
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
