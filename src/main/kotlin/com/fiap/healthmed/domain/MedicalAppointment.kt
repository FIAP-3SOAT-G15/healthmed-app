package com.fiap.healthmed.domain

import java.time.LocalDateTime

data class MedicalAppointment(
    val number: Long?,
    val doctor: Doctor,
    val patientDocument: Patient,
    val expectedStartTime: LocalDateTime,
    val estimatedTimeSpentInMinutes: Int = 50,
    val status: MedicalAppointmentStatus,
    val statusChangedAt: LocalDateTime?,
    val justificationCancellationByPatient: String?
)
