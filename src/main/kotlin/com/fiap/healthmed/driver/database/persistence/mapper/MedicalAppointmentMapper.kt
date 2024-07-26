package com.fiap.healthmed.driver.database.persistence.mapper

import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.domain.MedicalAppointmentStatus
import com.fiap.healthmed.driver.database.persistence.entities.MedicalAppointmentEntity
import org.mapstruct.factory.Mappers

interface MedicalAppointmentMapper {

    companion object {

        private val patientMapper: PatientMapper = Mappers.getMapper(PatientMapper::class.java)


        fun toDomain(entity: MedicalAppointmentEntity): MedicalAppointment {
            return MedicalAppointment(
                number = entity.number,
                status = MedicalAppointmentStatus.valueOf(entity.status),
                doctor = DoctorMapper.toDomain(entity.doctor),
                expectedStartTime = entity.expectedStartTime,
                statusChangedAt = entity.statusChangedAt,
                estimatedTimeSpentInMinutes = entity.estimatedTimeSpentInMinutes,
                patient = patientMapper.toDomain(entity.patient),
                justificationCancellationByPatient = entity.justificationCancellationByPatient,
            )
        }

        fun fromDomain(domain: MedicalAppointment): MedicalAppointmentEntity {
            return MedicalAppointmentEntity(
                number = domain.number,
                status = domain.status.name,
                doctor = DoctorMapper.fromDomain(domain.doctor),
                expectedStartTime = domain.expectedStartTime,
                statusChangedAt = domain.statusChangedAt,
                estimatedTimeSpentInMinutes = domain.estimatedTimeSpentInMinutes,
                patient = patientMapper.fromDomain(domain.patient),
                justificationCancellationByPatient = domain.justificationCancellationByPatient
            )
        }



    }
}
