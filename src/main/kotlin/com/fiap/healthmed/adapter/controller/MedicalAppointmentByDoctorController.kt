package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.driver.web.MedicalAppointmentByDoctorApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MedicalAppointmentByDoctorController : MedicalAppointmentByDoctorApi {
    override fun getAllMyAppointments(crm: String): ResponseEntity<List<MedicalAppointment>> {
        TODO("Not yet implemented")
    }

    override fun rejectAppointment(crm: String, appointmentNumber: String): ResponseEntity<MedicalAppointment> {
        TODO("Not yet implemented")
    }

    override fun acceptAppointment(crm: String, appointmentNumber: String): ResponseEntity<MedicalAppointment> {
        TODO("Not yet implemented")
    }
}