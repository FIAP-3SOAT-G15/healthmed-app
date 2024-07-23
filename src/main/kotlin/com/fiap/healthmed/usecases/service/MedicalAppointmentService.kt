package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.usecases.*
import java.time.LocalDateTime

class MedicalAppointmentService(
    private val medicalAppointmentGateway: MedicalAppointmentGateway
) : AcceptAppointmentUseCase,
    CancelAppointmentUseCase,
    ListMedicalAppointmentByPatientUseCase,
    ListMedicalAppointmentByDoctorUseCase,
    RejectAppointmentUseCase,
    ScheduleAppointmentUseCase
{
    override fun accept(crm: String, appointmentNumber: String): MedicalAppointment {
        return medicalAppointmentGateway.acceptAppointment(appointmentNumber)
    }

    override fun cancel(documentPatient: String, appointmentNumber: String, justification: String): MedicalAppointment {
        return medicalAppointmentGateway.cancelAppointment(appointmentNumber, justification)
    }

    override fun listByPatient(patientDocument: String): List<MedicalAppointment> {
        TODO("Not yet implemented")
    }

    override fun listByDoctor(crm: String): List<MedicalAppointment> {
        TODO("Not yet implemented")
    }

    override fun reject(crm: String, appointmentNumber: String): MedicalAppointment {
        return medicalAppointmentGateway.rejectAppointment(appointmentNumber)
    }

    override fun schedule(crm: String, document: String, scheduleAt: LocalDateTime) : MedicalAppointment {
        return medicalAppointmentGateway.createAppointment(crm, document, scheduleAt)
    }
}
