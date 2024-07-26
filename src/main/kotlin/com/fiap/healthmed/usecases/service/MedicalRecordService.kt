package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.MedicalRecordGateway
import com.fiap.healthmed.domain.MedicalRecord
import com.fiap.healthmed.usecases.AppendInMedicalRecordUseCase

class MedicalRecordService(private val medicalRecordGateway: MedicalRecordGateway) : AppendInMedicalRecordUseCase {

    override fun append(content: String, appointmentNumber: String): MedicalRecord {
        TODO("Not yet implemented")
    }

}
