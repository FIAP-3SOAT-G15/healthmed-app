package com.fiap.healthmed.domain

import java.time.LocalDateTime

data class MedicalAppointment(
    val number: Long? = null,
    val doctor: Doctor,
    val patientDocument: Patient,
    val expectedStartTime: LocalDateTime,
    val estimatedTimeSpentInMinutes: Int = ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES,
    val status: MedicalAppointmentStatus,
    val statusChangedAt: LocalDateTime?,
    val justificationCancellationByPatient: String? = null
)

const val ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES = 50
