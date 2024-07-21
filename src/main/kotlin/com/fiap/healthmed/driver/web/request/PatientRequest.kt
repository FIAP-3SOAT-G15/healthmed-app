package com.fiap.healthmed.driver.web.request

data class PatientRequest(
    val document: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val zipCode: String,
    val address: String
)