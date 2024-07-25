package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.domain.Patient
import com.fiap.healthmed.driver.database.persistence.jpa.PatientJpaRepository
import com.fiap.healthmed.driver.database.persistence.mapper.PatientMapper

class PatientGatewayImpl(
    private val patientJpaRepository: PatientJpaRepository,
    private val patientMapper: PatientMapper
) : PatientGateway {

    override fun createPatient(patient: Patient): Patient {
        val patientEntity = patientMapper.fromDomain(patient)
        val savedEntity = patientJpaRepository.save(patientEntity)
        return patientMapper.toDomain(savedEntity)
    }

    override fun updatePatient(patient: Patient): Patient {
        val patientEntity = patientMapper.fromDomain(patient)
        val updatedEntity = patientJpaRepository.save(patientEntity)
        return patientMapper.toDomain(updatedEntity)
    }
}
