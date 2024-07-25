package com.fiap.healthmed.adapter.controller

import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.domain.Patient
import com.fiap.healthmed.driver.web.PatientApi
import com.fiap.healthmed.driver.web.request.PatientRequest
import com.fiap.healthmed.driver.web.request.toDomain
import com.fiap.healthmed.usecases.CreatePatientUseCase
import com.fiap.healthmed.usecases.UpdatePatientUseCase
import com.fiap.healthmed.usecases.service.PatientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PatientController(
    patientGateway: PatientGateway
) : PatientApi {

    private val service: PatientService = PatientService(patientGateway)
    private val createPatientUseCase: CreatePatientUseCase = service
    private val updatePatientUseCase: UpdatePatientUseCase = service

    override fun create(request: PatientRequest): ResponseEntity<Patient> {
        return ResponseEntity.ok(
            createPatientUseCase.create(request.toDomain())
        )
    }

    override fun update(document: String, request: PatientRequest): ResponseEntity<Patient> {
        return ResponseEntity.ok(
            updatePatientUseCase.update(request.toDomain())
        )
    }
}
