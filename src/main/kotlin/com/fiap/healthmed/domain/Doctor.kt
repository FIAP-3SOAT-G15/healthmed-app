package com.fiap.healthmed.domain

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
    val availableTimes: AvailableTimes,
    val appointmentPrice: BigDecimal,
)
