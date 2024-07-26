package com.fiap.healthmed.driver.database.persistence.mapper

import com.fiap.healthmed.domain.MedicalRecord
import com.fiap.healthmed.driver.database.persistence.entities.MedicalRecordEntity

interface MedicalRecordMapper {

    companion object {


        fun toDomain(entity: MedicalRecordEntity): MedicalRecord {
            return MedicalRecord(
                number = entity.number,
                medicalAppointment = MedicalAppointmentMapper.toDomain(entity.medicalAppointmentNumber),
                content = null,
                fileLocation = entity.fileLocation,
                fileName = entity.fileName,
                provider = entity.provider,
                doctorCrm = entity.doctorCrm,
                patientDocument = entity.patientDocument,
            )
        }

        fun fromDomain(domain: MedicalRecord): MedicalRecordEntity {
            return MedicalRecordEntity(
                number = domain.number,
                medicalAppointmentNumber = MedicalAppointmentMapper.fromDomain(domain.medicalAppointment),
                doctorCrm = domain.doctorCrm,
                patientDocument = domain.patientDocument,
                fileLocation = domain.fileLocation,
                provider = domain.provider,
                fileName = domain.fileName,
            )
        }


    }

}
