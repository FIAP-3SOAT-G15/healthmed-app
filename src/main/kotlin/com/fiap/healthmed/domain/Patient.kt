package com.fiap.healthmed.domain

data class Patient(
    val document: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val zipCode: String,
    val address: String
)
