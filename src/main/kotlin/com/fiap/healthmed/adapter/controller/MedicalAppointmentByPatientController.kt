package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.driver.web.MedicalAppointmentByPatientApi
import com.fiap.healthmed.driver.web.request.JustificationCancellationRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MedicalAppointmentByPatientController : MedicalAppointmentByPatientApi {
    override fun scheduleAppointment(document: String, crm: String): ResponseEntity<MedicalAppointment> {
        TODO("Not yet implemented")
    }

    override fun cancelAppointment(
        document: String,
        appointmentNumber: String,
        requestBody: JustificationCancellationRequest
    ): ResponseEntity<MedicalAppointment> {
        TODO("Not yet implemented")
    }

    override fun getAllMyAppointments(document: String): ResponseEntity<List<MedicalAppointment>> {
        TODO("Not yet implemented")
    }
}