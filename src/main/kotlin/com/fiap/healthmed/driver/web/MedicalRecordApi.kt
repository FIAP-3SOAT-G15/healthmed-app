package com.fiap.healthmed.driver.web

import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.driver.web.request.MedicalRecordContentRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/healthmed/medical-record/{appointmentNumber}")
interface MedicalRecordApi {

    @PostMapping("/append")
    fun appendAnnotations(
        @RequestBody request: MedicalRecordContentRequest,
        @PathVariable appointmentNumber: String
    ): ResponseEntity<MedicalAppointment>
}
