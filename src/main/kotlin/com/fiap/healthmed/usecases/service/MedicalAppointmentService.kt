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
        TODO("Not yet implemented")
    }

    override fun cancel(documentPatient: String, appointmentNumber: Long, justification: String): MedicalAppointment {
        TODO("Not yet implemented")
    }

    override fun listByPatient(patientDocument: String): List<MedicalAppointment> {
        TODO("Not yet implemented")
    }

    override fun listByDoctor(crm: String): List<MedicalAppointment> {
        TODO("Not yet implemented")
    }

    override fun reject(crm: String, appointmentNumber: String): MedicalAppointment {
        TODO("Not yet implemented")
    }

    override fun schedule(crm: String, document: String, scheduleAt: LocalDateTime) : MedicalAppointment {
        TODO("Not yet implemented")
    }
}
