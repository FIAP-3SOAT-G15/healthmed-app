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

class DoctorGatewayImpl(
    private val doctorJpaRepository: DoctorJpaRepository,
) : DoctorGateway {

    private val doctorMapper: DoctorMapper = Mappers.getMapper(DoctorMapper::class.java)

    override fun createDoctor(doctor: Doctor): Doctor {
        getByCrm(doctor.crm)?.let {
            throw HealthMedException(
                errorType = ErrorType.DOCKER_ALREADY_EXISTS,
                message = "Doctor with CRM [${doctor.crm}] already exists",
            )
        }

        val doctorEntity = doctorMapper.fromDomain(doctor)
        val savedEntity = doctorJpaRepository.save(doctorEntity)
        return doctorMapper.toDomain(savedEntity)
    }

    override fun updateDoctor(doctor: Doctor): Doctor {
        getByCrm(doctor.crm)

        val updatedEntity = doctorJpaRepository.save(doctorMapper.fromDomain(doctor))
        return doctorMapper.toDomain(updatedEntity)
    }

    override fun updateDoctorAvailableTimes(crm: String, availableTimes: AvailableTimes): Doctor {
        val doctorEntity: DoctorEntity = getByCrm(crm) ?: throw HealthMedException(
            errorType = ErrorType.DOCTOR_NOT_FOUND,
            message = "Doctor with CRM [$crm] not found, nt",
        )

        val updatedEntity = doctorJpaRepository.save(doctorEntity)
        return doctorMapper.toDomain(updatedEntity)
    }


    override fun get(crm: String): Doctor {
        return getByCrm(crm)?.let(doctorMapper::toDomain)
            ?: throw HealthMedException(
                errorType = ErrorType.DOCTOR_NOT_FOUND,
                message = "Doctor with CRM [$crm] not found, nt",
            )
    }

    override fun searchDoctorWithName(name: String): List<Doctor> {
        return doctorJpaRepository.findByNameContains(name).map {
            doctorMapper.toDomain(it)
        }
    }

    override fun searchDoctorWithSpeciality(speciality: String): List<Doctor> {
        return doctorJpaRepository.findBySpecialty(speciality).map {
            doctorMapper.toDomain(it)
        }
    }

    override fun searchDoctorWithNameAndSpeciality(speciality: String, name: String): List<Doctor> {
        return doctorJpaRepository.findByNameContainsAndSpecialty(speciality, name).map {
            doctorMapper.toDomain(it)
        }
    }

    private fun getByCrm(crm: String): DoctorEntity? {
        return doctorJpaRepository.findByCrm(crm)
    }
}
