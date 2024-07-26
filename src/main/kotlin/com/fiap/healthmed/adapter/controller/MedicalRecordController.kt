package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.adapter.gateway.MedicalRecordGateway
import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.driver.web.MedicalRecordApi
import com.fiap.healthmed.driver.web.request.MedicalRecordContentRequest
import com.fiap.healthmed.usecases.service.MedicalRecordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MedicalRecordController(
    medicalRecordGateway: MedicalRecordGateway,
    appointmentGateway: MedicalAppointmentGateway): MedicalRecordApi {

    private val service = MedicalRecordService(medicalRecordGateway, appointmentGateway)

    override fun appendAnnotations(request: MedicalRecordContentRequest, appointmentNumber: String): ResponseEntity<MedicalAppointment> {
        return ResponseEntity.ok(
            service.append(content = request.content, appointmentNumber = appointmentNumber)
        )
    }
}
