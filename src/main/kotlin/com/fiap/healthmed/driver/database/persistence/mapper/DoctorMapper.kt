package com.fiap.healthmed.driver.database.persistence.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.driver.database.persistence.entities.DoctorEntity


interface DoctorMapper {

    companion object {

        fun toDomain(doctorEntity: DoctorEntity): Doctor {
            return Doctor(
                crm = doctorEntity.crm,
                name = doctorEntity.name,
                email = doctorEntity.email,
                document = doctorEntity.document,
                appointmentPrice = doctorEntity.appointmentPrice,
                specialty = doctorEntity.specialty,
                phoneNumber = doctorEntity.phoneNumber,
                availableTimes = convertAvailable(doctorEntity.doctorAvailableTimes),
                serviceAddress = doctorEntity.serviceAddress,
                serviceZipCode = doctorEntity.serviceZipCode,
            )
        }

        fun fromDomain(domain: Doctor): DoctorEntity {
            return DoctorEntity(
                crm = domain.crm,
                name = domain.name,
                email = domain.email,
                document = domain.document,
                appointmentPrice = domain.appointmentPrice,
                specialty = domain.specialty,
                phoneNumber = domain.phoneNumber,
                serviceAddress = domain.serviceAddress,
                serviceZipCode = domain.serviceZipCode,
                doctorAvailableTimes = convertAvailable(domain.availableTimes),
            )
        }

        fun convertAvailable(availableTimes: AvailableTimes): String {
            val mapper = ObjectMapper().registerModule(JavaTimeModule())
            return mapper.writeValueAsString(availableTimes)
        }

        fun convertAvailable(availableTimes: String): AvailableTimes {
            val mapper = ObjectMapper().registerModule(JavaTimeModule())
            return mapper.readValue(availableTimes, AvailableTimes::class.java)
        }
    }

}
