package com.fiap.healthmed.driver.database.persistence.mapper

import com.fiap.healthmed.domain.Patient
import com.fiap.healthmed.driver.database.persistence.entities.PatientEntity
import org.mapstruct.Mapper

@Mapper
interface PatientMapper {

    fun toDomain(patient: PatientEntity): Patient

    fun fromDomain(domain: Patient): PatientEntity
}
