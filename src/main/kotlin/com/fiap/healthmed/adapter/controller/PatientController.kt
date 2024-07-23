package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.domain.Patient
import com.fiap.healthmed.driver.web.PatientApi
import com.fiap.healthmed.driver.web.request.PatientRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PatientController : PatientApi {
    override fun create(request: PatientRequest): ResponseEntity<Patient> {
        TODO("Not yet implemented")
    }

    override fun update(document: String, request: PatientRequest): ResponseEntity<Patient> {
        TODO("Not yet implemented")
    }
}