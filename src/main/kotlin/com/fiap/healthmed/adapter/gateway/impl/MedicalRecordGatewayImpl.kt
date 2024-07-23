package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.MedicalRecordGateway
import com.fiap.healthmed.driver.database.persistence.jpa.MedicalRecordJpaRepository

class MedicalRecordGatewayImpl(private val medicalRecordJpaRepository: MedicalRecordJpaRepository) : MedicalRecordGateway {
}
