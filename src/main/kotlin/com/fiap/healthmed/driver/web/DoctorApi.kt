package com.fiap.healthmed.driver.web

import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.driver.web.request.AvailableTimesRequest
import com.fiap.healthmed.driver.web.request.DoctorRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/healthmed/doctor")
interface DoctorApi {

    @Operation(summary = "Cria um cadastro de um médico")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
        ],
    )
    @PostMapping
    fun create(@RequestBody request: DoctorRequest): ResponseEntity<Doctor>

    @Operation(summary = "Atualizar um cadastro de um médico")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
        ],
    )
    @PutMapping("{crm}")
    fun update(
        @PathVariable crm: String,
        @RequestBody request: DoctorRequest
    ): ResponseEntity<Doctor>

    @Operation(summary = "Atualizar os horarios disponiveis do medico")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
        ],
    )
    @PatchMapping("/{crm}/update-availability")
    fun updateAvailableTimes(
        @PathVariable crm: String,
        @RequestBody request: AvailableTimesRequest
    ): ResponseEntity<Doctor>

    @GetMapping("/search")
    fun search(@RequestParam query: Map<String, String>): ResponseEntity<List<Doctor>>

    @GetMapping("/{crm}")
    fun get(@PathVariable crm: String): ResponseEntity<Doctor>
}
