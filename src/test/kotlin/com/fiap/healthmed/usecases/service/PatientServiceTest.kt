package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.createPatient
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PatientServiceTest {

    private lateinit var patientGateway: PatientGateway
    private lateinit var patientService: PatientService

    @BeforeEach
    fun setUp() {
        patientGateway = mockk()
        patientService = PatientService(patientGateway)
    }

    private val patient = createPatient()

    @Test
    fun `should create a patient`() {
        every { patientGateway.createPatient(patient) } returns patient

        val result = patientService.create(patient)

        assertEquals(patient, result)
        verify { patientGateway.createPatient(patient) }
    }

    @Test
    fun `should update a patient`() {
        every { patientGateway.updatePatient(patient) } returns patient

        val result = patientService.update(patient)

        assertEquals(patient, result)
        verify { patientGateway.updatePatient(patient) }
    }
}

