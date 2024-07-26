package com.fiap.healthmed.adapter.gateway

import com.fiap.healthmed.domain.MedicalRecord

interface MedicalRecordGateway {

    fun sendFileProvider(record: MedicalRecord): MedicalRecord

    fun persistMetadata(record: MedicalRecord): MedicalRecord

}
