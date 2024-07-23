package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.driver.database.persistence.jpa.PatientJpaRepository

class PatientGatewayImpl(private val patientJpaRepository: PatientJpaRepository) : PatientGateway {
}
