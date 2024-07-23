package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.driver.web.DoctorApi
import com.fiap.healthmed.driver.web.request.AvailableTimesRequest
import com.fiap.healthmed.driver.web.request.DoctorRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class DoctorController : DoctorApi {
    override fun create(request: DoctorRequest): ResponseEntity<Doctor> {
        TODO("Not yet implemented")
    }

    override fun update(crm: String, request: DoctorRequest): ResponseEntity<Doctor> {
        TODO("Not yet implemented")
    }

    override fun updateAvailableTimes(crm: String, request: AvailableTimesRequest): ResponseEntity<Doctor> {
        TODO("Not yet implemented")
    }

    override fun search(query: Map<String, String>): ResponseEntity<List<Doctor>> {
        TODO("Not yet implemented")
    }
}