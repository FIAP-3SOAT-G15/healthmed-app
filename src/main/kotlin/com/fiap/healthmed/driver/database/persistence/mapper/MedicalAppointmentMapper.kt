package com.fiap.healthmed.driver.database.persistence.mapper

import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.driver.database.persistence.entities.MedicalAppointmentEntity
import org.mapstruct.Mapper

@Mapper
interface MedicalAppointmentMapper {
    fun toDomain(entity: MedicalAppointmentEntity): MedicalAppointment

    fun fromDomain(domain: MedicalAppointment): MedicalAppointmentEntity
}
