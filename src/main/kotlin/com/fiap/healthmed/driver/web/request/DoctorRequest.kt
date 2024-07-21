package com.fiap.healthmed.driver.web.request

import com.fiap.healthmed.domain.AvailableTimes
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
    val availableTimes: AvailableTimes,
    val appointmentPrice: BigDecimal,
)
