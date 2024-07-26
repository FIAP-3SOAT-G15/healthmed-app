package com.fiap.healthmed.driver.database.persistence.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.driver.database.persistence.entities.DoctorEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring", uses = [ConvertAvailableTime::class])
interface DoctorMapper {

    @Mapping(source = "doctorAvailableTimes", target = "availableTimes", qualifiedByName = ["stringToAvailableTimes"])
    fun toDomain(doctorEntity: DoctorEntity): Doctor

    @Mapping(source = "availableTimes", target = "doctorAvailableTimes", qualifiedByName = ["availableTimesToString"])
    fun fromDomain(domain: Doctor): DoctorEntity

}

@Component
class ConvertAvailableTime {

    @Named("availableTimesToString")
    fun convertAvailable(availableTimes: AvailableTimes): String {
        val mapper = ObjectMapper().registerModule(JavaTimeModule());
        return mapper.writeValueAsString(availableTimes);
    }

    @Named("stringToAvailableTimes")
    fun convertAvailable(availableTimes: String): AvailableTimes {
        val mapper = ObjectMapper().registerModule(JavaTimeModule());
        return mapper.readValue(availableTimes, AvailableTimes::class.java);
    }
}
