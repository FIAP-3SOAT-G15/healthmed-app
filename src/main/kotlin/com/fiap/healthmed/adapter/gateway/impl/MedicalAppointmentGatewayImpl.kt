package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.domain.*
import com.fiap.healthmed.domain.errors.ErrorType
import com.fiap.healthmed.domain.errors.HealthMedException
import com.fiap.healthmed.driver.database.persistence.jpa.MedicalAppointmentJpaRepository
import com.fiap.healthmed.driver.database.persistence.mapper.MedicalAppointmentMapper
import org.mapstruct.factory.Mappers
import java.time.LocalDateTime

class MedicalAppointmentGatewayImpl(private val medicalAppointmentJpaRepository: MedicalAppointmentJpaRepository) :

    MedicalAppointmentGateway {
    private val mapper = Mappers.getMapper(MedicalAppointmentMapper::class.java)

    override fun updateAppointmentStatus(
        appointmentNumber: String,
        appointmentStatus: MedicalAppointmentStatus,
        justification: String
    ): MedicalAppointment {
        val appointment = findAppointment(appointmentNumber).copy(
            status = appointmentStatus,
            justificationCancellationByPatient = justification
        )
        medicalAppointmentJpaRepository.save(mapper.fromDomain(appointment))
        return appointment
    }

    override fun findAppointment(appointmentNumber: String): MedicalAppointment {
        return medicalAppointmentJpaRepository.findById(appointmentNumber.toLong()).map(mapper::toDomain).orElseThrow {
            HealthMedException(
                errorType = ErrorType.APPOINTMENT_NOT_FOUND,
                message = "Appointment [$appointmentNumber] not found"
            )
        }
    }

    override fun acceptAppointment(appointmentNumber: String): MedicalAppointment {
        return updateAppointmentStatus(appointmentNumber, MedicalAppointmentStatus.ACCEPTED)
    }

    override fun rejectAppointment(appointmentNumber: String): MedicalAppointment {
        return updateAppointmentStatus(appointmentNumber, MedicalAppointmentStatus.REJECTED)
    }

    override fun createAppointment(doctor: Doctor, patient: Patient, scheduleAt: LocalDateTime): MedicalAppointment {
        return medicalAppointmentJpaRepository.save(
            MedicalAppointment(
                doctor = doctor,
                patient = patient,
                status = MedicalAppointmentStatus.SCHEDULED,
                expectedStartTime = scheduleAt,
                estimatedTimeSpentInMinutes = ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES,
                statusChangedAt = LocalDateTime.now(),
            ).let { mapper.fromDomain(it) }
        ).let(mapper::toDomain)
    }


    override fun findAppointmentsByPatient(patientDocument: String): List<MedicalAppointment> {
        return medicalAppointmentJpaRepository.findByPatientDocument(patientDocument).map(mapper::toDomain)
    }

    override fun findAppointmentsByDoctor(crm: String): List<MedicalAppointment> {
        return medicalAppointmentJpaRepository.findByDoctorCrm(crm).map(mapper::toDomain)
    }

    override fun findAppointmentsByTimeAndDoctor(
        crm: String,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ): List<MedicalAppointment> {
        return medicalAppointmentJpaRepository.findByDoctorCrmAndExpectedStartTimeBetween(
            crm = crm,
            start = startTime,
            end = endTime
        ).map(mapper::toDomain)
    }

    override fun cancelAppointment(appointmentNumber: String, justification: String): MedicalAppointment {
        return updateAppointmentStatus(appointmentNumber, MedicalAppointmentStatus.CANCELED, justification)
    }
}
