package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.domain.ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES
import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.domain.errors.ErrorType
import com.fiap.healthmed.domain.errors.HealthMedException
import com.fiap.healthmed.usecases.*
import java.time.LocalDateTime

class MedicalAppointmentService(
    private val medicalAppointmentGateway: MedicalAppointmentGateway,
    private val doctorGateway: DoctorGateway,
    private val patientGateway: PatientGateway,
) : AcceptAppointmentUseCase,
    CancelAppointmentUseCase,
    ListMedicalAppointmentByPatientUseCase,
    ListMedicalAppointmentByDoctorUseCase,
    RejectAppointmentUseCase,
    ScheduleAppointmentUseCase {

    override fun accept(crm: String, appointmentNumber: String): MedicalAppointment {
        return medicalAppointmentGateway.acceptAppointment(appointmentNumber)
    }

    override fun cancel(documentPatient: String, appointmentNumber: String, justification: String): MedicalAppointment {
        return medicalAppointmentGateway.cancelAppointment(appointmentNumber, justification)
    }

    override fun listByPatient(patientDocument: String): List<MedicalAppointment> {
        return medicalAppointmentGateway.findAppointmentsByPatient(patientDocument)
    }

    override fun listByDoctor(crm: String): List<MedicalAppointment> {
        return medicalAppointmentGateway.findAppointmentsByDoctor(crm)
    }

    override fun reject(crm: String, appointmentNumber: String): MedicalAppointment {
        return medicalAppointmentGateway.rejectAppointment(appointmentNumber)
    }

    override fun schedule(crm: String, document: String, scheduleAt: LocalDateTime): MedicalAppointment {
        val doctor = doctorGateway.get(crm)
        val patient = patientGateway.get(document)

        if (!doctor.availableTimes.canAccept(scheduleAt)) {
            throw HealthMedException(
                errorType = ErrorType.IMCOMPATIBLE_SCHEDULE,
                message = "Time incompatible with the doctor's schedule"
            )
        }

        val appointments = medicalAppointmentGateway.findAppointmentsByTimeAndDoctor(
            crm = crm, startTime = scheduleAt, endTime = scheduleAt.plusMinutes(ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES.toLong())
        )

        if (appointments.isNotEmpty()) {
            throw HealthMedException(
                errorType = ErrorType.UNAVAILABLE_TIME,
                message = "Time unavailable because the doctor already has another appointment"
            )
        }

        return medicalAppointmentGateway.createAppointment(
            doctor = doctor,
            patient = patient,
            scheduleAt = scheduleAt,
        )
    }
}

