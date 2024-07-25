package com.fiap.healthmed.driver.web

import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.driver.web.request.JustificationCancellationRequest
import com.fiap.healthmed.driver.web.request.TimeAndDateToScheduleRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/healthmed/medical-appointment/{document}")
interface MedicalAppointmentByPatientApi {

    @PostMapping("/{crm}")
    fun scheduleAppointment(
        @PathVariable document: String,
        @PathVariable crm: String,
        @RequestBody requestBody: TimeAndDateToScheduleRequest
    ): ResponseEntity<MedicalAppointment>

    @PostMapping("/cancel/{appointmentNumber}")
    fun cancelAppointment(
        @PathVariable document: String,
        @PathVariable appointmentNumber: String,
        @RequestBody requestBody: JustificationCancellationRequest
    ): ResponseEntity<MedicalAppointment>

    @GetMapping
    fun getAllMyAppointments(@PathVariable document: String): ResponseEntity<List<MedicalAppointment>>
}
