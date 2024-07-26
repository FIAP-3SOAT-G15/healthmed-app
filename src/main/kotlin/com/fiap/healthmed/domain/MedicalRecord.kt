package com.fiap.healthmed.domain

data class MedicalRecord(
    val number: Long? = null,
    val medicalAppointment: MedicalAppointment,
    val doctorCrm: String,
    val patientDocument: String,
    val provider: String = "S3",
    val fileLocation: String,
    val fileName: String,
    val content: String?
)
