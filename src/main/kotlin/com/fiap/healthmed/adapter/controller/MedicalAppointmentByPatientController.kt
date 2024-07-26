package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.driver.web.MedicalAppointmentByPatientApi
import com.fiap.healthmed.driver.web.request.JustificationCancellationRequest
import com.fiap.healthmed.driver.web.request.TimeAndDateToScheduleRequest
import com.fiap.healthmed.usecases.CancelAppointmentUseCase
import com.fiap.healthmed.usecases.ListMedicalAppointmentByPatientUseCase
import com.fiap.healthmed.usecases.ScheduleAppointmentUseCase
import com.fiap.healthmed.usecases.service.MedicalAppointmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MedicalAppointmentByPatientController(
    medicalAppointmentGateway: MedicalAppointmentGateway,
    doctorGateway: DoctorGateway,
    patientGateway: PatientGateway
) : MedicalAppointmentByPatientApi {

    private val service = MedicalAppointmentService(medicalAppointmentGateway, doctorGateway, patientGateway)
    private val scheduleAppointmentUseCase: ScheduleAppointmentUseCase = service
    private val cancelAppointmentUseCase: CancelAppointmentUseCase = service
    private val listMedicalAppointmentByPatientUseCase: ListMedicalAppointmentByPatientUseCase = service


    override fun scheduleAppointment(
        document: String,
        crm: String,
        requestBody: TimeAndDateToScheduleRequest
    ): ResponseEntity<MedicalAppointment> {
        return ResponseEntity.ok(
            scheduleAppointmentUseCase.schedule(crm, document, requestBody.scheduleAt)
        )
    }

    override fun cancelAppointment(
        document: String,
        appointmentNumber: String,
        requestBody: JustificationCancellationRequest
    ): ResponseEntity<MedicalAppointment> {
        return ResponseEntity.ok(
            cancelAppointmentUseCase.cancel(
                document, appointmentNumber, requestBody.text
            )
        )
    }

    override fun getAllMyAppointments(document: String): ResponseEntity<List<MedicalAppointment>> {
        return ResponseEntity.ok(
            listMedicalAppointmentByPatientUseCase.listByPatient(document)
        )
    }
}
