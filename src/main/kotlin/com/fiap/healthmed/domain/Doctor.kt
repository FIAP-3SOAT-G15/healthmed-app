package com.fiap.healthmed.domain

import com.fiap.healthmed.driver.web.request.AvailableTimesRequest
import java.math.BigDecimal

data class Doctor(
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
