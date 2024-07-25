package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.driver.web.DoctorApi
import com.fiap.healthmed.driver.web.request.AvailableTimesRequest
import com.fiap.healthmed.driver.web.request.DoctorRequest
import com.fiap.healthmed.driver.web.request.toDomain
import com.fiap.healthmed.usecases.CreateDoctorUseCase
import com.fiap.healthmed.usecases.SearchDoctorUseCase
import com.fiap.healthmed.usecases.UpdateAvailableTimeDoctorUseCase
import com.fiap.healthmed.usecases.UpdateDoctorUseCase
import com.fiap.healthmed.usecases.service.DoctorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class DoctorController(
    doctorGateway: DoctorGateway,
) : DoctorApi {

    private val service = DoctorService(doctorGateway)
    private val createDoctorUseCase : CreateDoctorUseCase = service
    private val updateDoctorUseCase : UpdateDoctorUseCase = service
    private val updateAvailableTimeDoctorUseCase : UpdateAvailableTimeDoctorUseCase = service
    private val searchDoctorUseCase : SearchDoctorUseCase = service


    override fun create(request: DoctorRequest): ResponseEntity<Doctor> {
        return ResponseEntity.ok(createDoctorUseCase.create(request.toDomain()))
    }

    override fun update(crm: String, request: DoctorRequest): ResponseEntity<Doctor> {
        return ResponseEntity.ok(updateDoctorUseCase.update(request.toDomain()))
    }

    override fun updateAvailableTimes(crm: String, request: AvailableTimesRequest): ResponseEntity<Doctor> {
        return ResponseEntity.ok(updateAvailableTimeDoctorUseCase.updateAvailableTime(crm, request.toDomain()))
    }

    override fun search(query: Map<String, String>): ResponseEntity<List<Doctor>> {
        return ResponseEntity.ok(searchDoctorUseCase.search(query))
    }

    override fun get(crm: String): ResponseEntity<Doctor> {
        return ResponseEntity.ok(searchDoctorUseCase.get(crm))

    }
}
