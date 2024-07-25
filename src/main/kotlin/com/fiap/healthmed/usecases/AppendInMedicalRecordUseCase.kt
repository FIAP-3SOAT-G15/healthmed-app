package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.MedicalRecord

interface AppendInMedicalRecordUseCase {
    fun append(content: String, appointmentNumber: String): MedicalRecord
}
