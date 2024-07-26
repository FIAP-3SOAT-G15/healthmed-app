package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.driver.web.MedicalRecordApi
import com.fiap.healthmed.driver.web.request.MedicalRecordContentRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MedicalRecordController: MedicalRecordApi {


    override fun appendAnnotations(
        request: MedicalRecordContentRequest,
        appointmentNumber: String
    ): ResponseEntity<MedicalAppointment> {
        TODO("Not yet implemented")
    }
}
