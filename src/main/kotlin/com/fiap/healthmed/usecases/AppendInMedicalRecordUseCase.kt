package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.MedicalAppointment

interface AppendInMedicalRecordUseCase {
    fun append(content: String, appointmentNumber: String): MedicalAppointment
}
