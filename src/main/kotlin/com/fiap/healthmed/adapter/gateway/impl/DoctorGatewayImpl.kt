package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.driver.database.persistence.jpa.DoctorJpaRepository
import com.fiap.healthmed.driver.database.persistence.mapper.DoctorMapper

class DoctorGatewayImpl(
    private val doctorJpaRepository: DoctorJpaRepository,
    private val doctorMapper: DoctorMapper
) : DoctorGateway {
    override fun createDoctor(doctor: Doctor): Doctor {
        val doctorEntity = doctorMapper.fromDomain(doctor)
        val savedEntity = doctorJpaRepository.save(doctorEntity)
        return doctorMapper.toDomain(savedEntity)
    }

    override fun updateDoctor(doctor: Doctor): Doctor {
        val doctorEntity = doctorMapper.fromDomain(doctor)
        val updatedEntity = doctorJpaRepository.save(doctorEntity)
        return doctorMapper.toDomain(updatedEntity)
    }

    override fun updateDoctorAvailableTimes(crm: String, availableTimes: AvailableTimes): Doctor {
        val doctorEntity = doctorJpaRepository.findByCrm(crm)
            ?: throw NoSuchElementException("Doctor with CRM [$crm] not found, nt")

        val updatedEntity = doctorJpaRepository.save(doctorEntity)
        return doctorMapper.toDomain(updatedEntity)
    }

    override fun searchDoctors(query: Map<String, String>): List<Doctor> {
        val doctors = doctorJpaRepository.search(query)
        return doctors.map { doctorMapper.toDomain(it) }
    }
}
