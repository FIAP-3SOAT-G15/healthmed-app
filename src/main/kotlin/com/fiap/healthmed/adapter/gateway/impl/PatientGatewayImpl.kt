package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.domain.Patient
import com.fiap.healthmed.domain.errors.ErrorType
import com.fiap.healthmed.domain.errors.HealthMedException
import com.fiap.healthmed.driver.database.persistence.jpa.PatientJpaRepository
import com.fiap.healthmed.driver.database.persistence.mapper.PatientMapper
import org.mapstruct.factory.Mappers

class PatientGatewayImpl(
    private val patientJpaRepository: PatientJpaRepository,
) : PatientGateway {

    private val patientMapper: PatientMapper = Mappers.getMapper(PatientMapper::class.java)


    override fun createPatient(patient: Patient): Patient {
        val patientEntityOp = patientJpaRepository.findById(patient.document)

        if (patientEntityOp.isPresent) {
            throw HealthMedException(
                errorType = ErrorType.PATIENT_ALREADY_EXISTS,
                message = "patient ${patient.document} already exists"
            )
        }

        val patientEntity = patientMapper.fromDomain(patient)
        val savedEntity = patientJpaRepository.save(patientEntity)
        return patientMapper.toDomain(savedEntity)
    }

    override fun updatePatient(patient: Patient): Patient {
        val patientEntityOp = patientJpaRepository.findById(patient.document)

        if (patientEntityOp.isEmpty) {
            throw HealthMedException(
                errorType = ErrorType.PATIENT_NOT_FOUND,
                message = "patient ${patient.document} not found"
            )
        }
        val patientEntity = patientMapper.fromDomain(patient)
        val updatedEntity = patientJpaRepository.save(patientEntity)
        return patientMapper.toDomain(updatedEntity)
    }

    override fun get(document: String): Patient {
        val patient = patientJpaRepository.findById(document).map(patientMapper::toDomain).orElse(null)

        return patient ?: throw HealthMedException(
            errorType = ErrorType.PATIENT_NOT_FOUND,
            message = "patient $document not found"
        )
    }
}
