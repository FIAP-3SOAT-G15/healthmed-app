package com.fiap.healthmed.driver.web

import com.fiap.healthmed.domain.Patient
import com.fiap.healthmed.driver.web.request.PatientRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/healthmed/patient")
interface PatientApi {

    @Operation(summary = "Cria um cadastro de um paciente")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
        ],
    )
    @PostMapping
    fun create(@RequestBody request: PatientRequest): ResponseEntity<Patient>

    @Operation(summary = "Atualizar um cadastro de um paciente")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
        ],
    )
    @PutMapping("{document}")
    fun update(
        @PathVariable document: String,
        @RequestBody request: PatientRequest
    ): ResponseEntity<Patient>
}
