package com.fiap.healthmed.driver.database.persistence.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.driver.database.persistence.entities.DoctorEntity
import org.mapstruct.Mapper

@Mapper
interface DoctorMapper {

    fun toDomain(doctorEntity: DoctorEntity): Doctor

    fun fromDomain(domain: Doctor): DoctorEntity

    fun convertAvailable(availableTimes: AvailableTimes): String {
        val mapper = ObjectMapper().registerModule(JavaTimeModule());
        return mapper.writeValueAsString(availableTimes);
    }

    fun convertAvailable(availableTimes: String): AvailableTimes {
        val mapper = ObjectMapper().registerModule(JavaTimeModule());
        return mapper.readValue(availableTimes, AvailableTimes::class.java);
    }
}
