package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.adapter.gateway.MedicalRecordGateway
import com.fiap.healthmed.createDoctor
import com.fiap.healthmed.createMedicalAppointment
import com.fiap.healthmed.createPatient
import com.fiap.healthmed.domain.MedicalAppointmentStatus
import com.fiap.healthmed.domain.MedicalRecord
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MedicalRecordServiceTest {

    private lateinit var medicalRecordGateway: MedicalRecordGateway
    private lateinit var medicalAppointmentGateway: MedicalAppointmentGateway
    private lateinit var medicalRecordService: MedicalRecordService

    @BeforeEach
    fun setUp() {
        medicalRecordGateway = mockk()
        medicalAppointmentGateway = mockk()
        medicalRecordService = MedicalRecordService(medicalRecordGateway, medicalAppointmentGateway)
    }

    private val doctor = createDoctor()
    private val patient = createPatient()
    private val medicalAppointment = createMedicalAppointment()

    @Test
    fun `should append to medical record and update appointment status`() {
        val content = "Bla bla bla"
        val appointmentNumber = "123"
        val updatedAppointment = medicalAppointment.copy(status = MedicalAppointmentStatus.FINISHED)

        val expectedRecord = MedicalRecord(
            medicalAppointment = medicalAppointment,
            doctorCrm = doctor.crm,
            patientDocument = patient.document,
            fileLocation = "/fake/path/to/medicalrecord",
            content = content,
            fileName = "123-111.222.txt"
        )

        every { medicalAppointmentGateway.findAppointment(appointmentNumber) } returns medicalAppointment
        every { medicalRecordGateway.sendFileProvider(expectedRecord) } returns expectedRecord
        every { medicalRecordGateway.persistMetadata(expectedRecord) } returns mockk()
        every { medicalAppointmentGateway.updateAppointmentStatus(appointmentNumber, MedicalAppointmentStatus.FINISHED) } returns updatedAppointment

        val result = medicalRecordService.append(content, appointmentNumber)

        assertEquals(updatedAppointment, result)
        verify { medicalAppointmentGateway.findAppointment(appointmentNumber) }
        verify { medicalRecordGateway.sendFileProvider(expectedRecord) }
        verify { medicalRecordGateway.persistMetadata(expectedRecord) }
        verify { medicalAppointmentGateway.updateAppointmentStatus(appointmentNumber, MedicalAppointmentStatus.FINISHED) }
    }
}

