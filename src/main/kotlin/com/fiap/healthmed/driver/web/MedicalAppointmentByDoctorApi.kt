package com.fiap.healthmed.driver.web

import com.fiap.healthmed.domain.MedicalAppointment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/healthmed/medical-appointment/{crm}")
interface MedicalAppointmentByDoctorApi {

    @GetMapping
    fun getAllMyAppointments(@PathVariable crm: String): ResponseEntity<List<MedicalAppointment>>

    @PostMapping("/reject/{appointmentNumber}")
    fun rejectAppointment(
        @PathVariable crm: String,
        @PathVariable appointmentNumber: String,
    ): ResponseEntity<MedicalAppointment>

    @PostMapping("/cancel/{appointmentNumber}")
    fun acceptAppointment(
        @PathVariable crm: String,
        @PathVariable appointmentNumber: String,
    ): ResponseEntity<MedicalAppointment>
}
