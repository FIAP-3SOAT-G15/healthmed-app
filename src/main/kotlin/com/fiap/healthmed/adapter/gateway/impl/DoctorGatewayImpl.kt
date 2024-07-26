package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.domain.errors.ErrorType
import com.fiap.healthmed.domain.errors.HealthMedException
import com.fiap.healthmed.driver.database.persistence.entities.DoctorEntity
import com.fiap.healthmed.driver.database.persistence.jpa.DoctorJpaRepository
import com.fiap.healthmed.driver.database.persistence.mapper.DoctorMapper
import org.mapstruct.factory.Mappers
import org.springframework.transaction.annotation.Transactional

open class DoctorGatewayImpl(
    private val doctorJpaRepository: DoctorJpaRepository,
) : DoctorGateway {

    @Transactional
    override fun createDoctor(doctor: Doctor): Doctor {
        getByCrm(doctor.crm)?.let {
            throw HealthMedException(
                errorType = ErrorType.DOCKER_ALREADY_EXISTS,
                message = "Doctor with CRM [${doctor.crm}] already exists",
            )
        }

        val doctorEntity = DoctorMapper.fromDomain(doctor)
        val savedEntity = doctorJpaRepository.save(doctorEntity)
        return DoctorMapper.toDomain(savedEntity)
    }

    @Transactional
    override fun updateDoctor(doctor: Doctor): Doctor {
        getByCrm(doctor.crm)

        val updatedEntity = doctorJpaRepository.save(DoctorMapper.fromDomain(doctor))
        return DoctorMapper.toDomain(updatedEntity)
    }

    override fun get(crm: String): Doctor {
        return getByCrm(crm)?.let(DoctorMapper::toDomain)
            ?: throw HealthMedException(
                errorType = ErrorType.DOCTOR_NOT_FOUND,
                message = "Doctor with CRM [$crm] not found, nt",
            )
    }

    override fun searchDoctorWithName(name: String): List<Doctor> {
        return doctorJpaRepository.findByNameContains(name).map {
            DoctorMapper.toDomain(it)
        }
    }

    override fun searchDoctorWithSpeciality(speciality: String): List<Doctor> {
        return doctorJpaRepository.findBySpecialty(speciality).map {
            DoctorMapper.toDomain(it)
        }
    }

    override fun searchDoctorWithNameAndSpeciality(speciality: String, name: String): List<Doctor> {
        return doctorJpaRepository.findByNameContainsAndSpecialty(name, speciality).map {
            DoctorMapper.toDomain(it)
        }
    }

    private fun getByCrm(crm: String): DoctorEntity? {
        return doctorJpaRepository.findByCrm(crm)
    }
}
