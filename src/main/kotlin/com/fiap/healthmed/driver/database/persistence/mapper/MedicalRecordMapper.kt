package com.fiap.healthmed.driver.database.persistence.mapper

import com.fiap.healthmed.domain.MedicalRecord
import com.fiap.healthmed.driver.database.persistence.entities.MedicalRecordEntity
import org.mapstruct.Mapper

@Mapper
interface MedicalRecordMapper {
    fun toDomain(entity: MedicalRecordEntity): MedicalRecord

    fun fromDomain(domain: MedicalRecord): MedicalRecordEntity
}
