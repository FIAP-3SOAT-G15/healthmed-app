package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.driver.web.MedicalAppointmentByDoctorApi
import com.fiap.healthmed.usecases.AcceptAppointmentUseCase
import com.fiap.healthmed.usecases.ListMedicalAppointmentByDoctorUseCase
import com.fiap.healthmed.usecases.RejectAppointmentUseCase
import com.fiap.healthmed.usecases.service.MedicalAppointmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MedicalAppointmentByDoctorController(
    medicalAppointmentGateway: MedicalAppointmentGateway
) : MedicalAppointmentByDoctorApi {


    private val service = MedicalAppointmentService(medicalAppointmentGateway)
    private val rejectAppointmentUseCase: RejectAppointmentUseCase = service
    private val acceptAppointmentUseCase: AcceptAppointmentUseCase = service
    private val listMedicalAppointmentByDoctorUseCase: ListMedicalAppointmentByDoctorUseCase = service


    override fun getAllMyAppointments(crm: String): ResponseEntity<List<MedicalAppointment>> {
        return ResponseEntity.ok(
            listMedicalAppointmentByDoctorUseCase.listByDoctor(crm)
        )
    }

    override fun rejectAppointment(crm: String, appointmentNumber: String): ResponseEntity<MedicalAppointment> {
        return ResponseEntity.ok(
            rejectAppointmentUseCase.reject(crm, appointmentNumber)
        )
    }

    override fun acceptAppointment(crm: String, appointmentNumber: String): ResponseEntity<MedicalAppointment> {
        return ResponseEntity.ok(
            acceptAppointmentUseCase.accept(crm, appointmentNumber)
        )
    }
}
