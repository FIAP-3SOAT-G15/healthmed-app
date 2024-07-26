package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.domain.*
import com.fiap.healthmed.domain.errors.ErrorType
import com.fiap.healthmed.domain.errors.HealthMedException
import com.fiap.healthmed.driver.database.persistence.jpa.MedicalAppointmentJpaRepository
import com.fiap.healthmed.driver.database.persistence.mapper.MedicalAppointmentMapper
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

open class MedicalAppointmentGatewayImpl(private val medicalAppointmentJpaRepository: MedicalAppointmentJpaRepository) :

    MedicalAppointmentGateway {


    override fun updateAppointmentStatus(
        appointmentNumber: String,
        appointmentStatus: MedicalAppointmentStatus,
        justification: String
    ): MedicalAppointment {
        val appointment = findAppointment(appointmentNumber).copy(
            status = appointmentStatus,
            justificationCancellationByPatient = justification
        )
        medicalAppointmentJpaRepository.save(MedicalAppointmentMapper.fromDomain(appointment))
        return appointment
    }

    override fun findAppointment(appointmentNumber: String): MedicalAppointment {
        return medicalAppointmentJpaRepository.findById(appointmentNumber.toLong()).map(MedicalAppointmentMapper::toDomain).orElseThrow {
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

    @Transactional
    override fun createAppointment(doctor: Doctor, patient: Patient, scheduleAt: LocalDateTime): MedicalAppointment {
        return medicalAppointmentJpaRepository.save(
            MedicalAppointment(
                doctor = doctor,
                patient = patient,
                status = MedicalAppointmentStatus.SCHEDULED,
                expectedStartTime = scheduleAt,
                estimatedTimeSpentInMinutes = ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES,
                statusChangedAt = LocalDateTime.now(),
            ).let { MedicalAppointmentMapper.fromDomain(it) }
        ).let(MedicalAppointmentMapper::toDomain)
    }


    override fun findAppointmentsByPatient(patientDocument: String): List<MedicalAppointment> {
        return medicalAppointmentJpaRepository.findByPatientDocument(patientDocument).map(MedicalAppointmentMapper::toDomain)
    }

    override fun findAppointmentsByDoctor(crm: String): List<MedicalAppointment> {
        return medicalAppointmentJpaRepository.findByDoctorCrm(crm).map(MedicalAppointmentMapper::toDomain)
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
        ).map(MedicalAppointmentMapper::toDomain)
    }

    override fun cancelAppointment(appointmentNumber: String, justification: String): MedicalAppointment {
        return updateAppointmentStatus(appointmentNumber, MedicalAppointmentStatus.CANCELED, justification)
    }
}
