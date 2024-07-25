package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.domain.MedicalAppointmentStatus
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

    override fun createAppointment(crm: String, document: String, scheduleAt: LocalDateTime): MedicalAppointment {
        TODO()
    }


    override fun cancelAppointment(appointmentNumber: String, justification: String): MedicalAppointment {
        return updateAppointmentStatus(appointmentNumber, MedicalAppointmentStatus.CANCELED, justification)
    }
}
