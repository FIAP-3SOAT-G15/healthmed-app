package com.fiap.healthmed.driver.web.request

import com.fiap.healthmed.domain.Patient

data class PatientRequest(
    val document: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val zipCode: String,
    val address: String
)

fun PatientRequest.toDomain(): Patient = Patient(
    document = document,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    zipCode  = zipCode,
    address = address
)
