package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.createDoctor
import com.fiap.healthmed.createMedicalAppointment
import com.fiap.healthmed.createPatient
import com.fiap.healthmed.domain.ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES
import com.fiap.healthmed.domain.errors.ErrorType
import com.fiap.healthmed.domain.errors.HealthMedException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class MedicalAppointmentServiceTest {

    private lateinit var medicalAppointmentGateway: MedicalAppointmentGateway
    private lateinit var doctorGateway: DoctorGateway
    private lateinit var patientGateway: PatientGateway
    private lateinit var medicalAppointmentService: MedicalAppointmentService

    @BeforeEach
    fun setUp() {
        medicalAppointmentGateway = mockk()
        doctorGateway = mockk()
        patientGateway = mockk()
        medicalAppointmentService = MedicalAppointmentService(medicalAppointmentGateway, doctorGateway, patientGateway)
    }

    private val doctor = createDoctor()
    private val patient = createPatient()
    private val medicalAppointment = createMedicalAppointment(
        number = 1L,
        expectedStartTime = LocalDateTime.of(2024, 7, 26, 10, 0),
    )

    @Test
    fun `should accept an appointment`() {
        every { medicalAppointmentGateway.acceptAppointment(medicalAppointment.number.toString()) } returns medicalAppointment

        val result = medicalAppointmentService.accept(doctor.crm, medicalAppointment.number.toString())

        assertEquals(medicalAppointment, result)
        verify { medicalAppointmentGateway.acceptAppointment(medicalAppointment.number.toString()) }
    }

    @Test
    fun `should cancel an appointment`() {
        val justification = "No longer available"
        every { medicalAppointmentGateway.cancelAppointment(medicalAppointment.number.toString(), justification) } returns medicalAppointment.copy(
            justificationCancellationByPatient = justification
        )

        val result = medicalAppointmentService.cancel(patient.document, medicalAppointment.number.toString(), justification)

        assertEquals(medicalAppointment.copy(justificationCancellationByPatient = justification), result)
        verify { medicalAppointmentGateway.cancelAppointment(medicalAppointment.number.toString(), justification) }
    }

    @Test
    fun `should list appointments by patient`() {
        every { medicalAppointmentGateway.findAppointmentsByPatient(patient.document) } returns listOf(medicalAppointment)

        val result = medicalAppointmentService.listByPatient(patient.document)

        assertEquals(listOf(medicalAppointment), result)
        verify { medicalAppointmentGateway.findAppointmentsByPatient(patient.document) }
    }

    @Test
    fun `should list appointments by doctor`() {
        every { medicalAppointmentGateway.findAppointmentsByDoctor(doctor.crm) } returns listOf(medicalAppointment)

        val result = medicalAppointmentService.listByDoctor(doctor.crm)

        assertEquals(listOf(medicalAppointment), result)
        verify { medicalAppointmentGateway.findAppointmentsByDoctor(doctor.crm) }
    }

    @Test
    fun `should reject an appointment`() {
        every { medicalAppointmentGateway.rejectAppointment(medicalAppointment.number.toString()) } returns medicalAppointment

        val result = medicalAppointmentService.reject(doctor.crm, medicalAppointment.number.toString())

        assertEquals(medicalAppointment, result)
        verify { medicalAppointmentGateway.rejectAppointment(medicalAppointment.number.toString()) }
    }

    @Test
    fun `should schedule an appointment if time is available`() {
        val scheduleAt = LocalDateTime.of(2024, 7, 26, 10, 0)
        val endTime = scheduleAt.plusMinutes(ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES.toLong())
        every { doctorGateway.get(doctor.crm) } returns doctor
        every { patientGateway.get(patient.document) } returns patient
        every { medicalAppointmentGateway.findAppointmentsByTimeAndDoctor(doctor.crm, scheduleAt, endTime) } returns emptyList()
        every { medicalAppointmentGateway.createAppointment(doctor, patient, scheduleAt) } returns medicalAppointment

        val result = medicalAppointmentService.schedule(doctor.crm, patient.document, scheduleAt)

        assertEquals(medicalAppointment, result)
        verify { doctorGateway.get(doctor.crm) }
        verify { patientGateway.get(patient.document) }
        verify { medicalAppointmentGateway.findAppointmentsByTimeAndDoctor(doctor.crm, scheduleAt, endTime) }
        verify { medicalAppointmentGateway.createAppointment(doctor, patient, scheduleAt) }
    }

    @Test
    fun `should throw HealthMedException if time is incompatible with doctor's schedule`() {
        val scheduleAt = LocalDateTime.of(2024, 7, 26, 18, 0) // Outside available time
        every { doctorGateway.get(doctor.crm) } returns doctor
        every { patientGateway.get(patient.document) } returns patient

        val exception = assertThrows<HealthMedException> {
            medicalAppointmentService.schedule(doctor.crm, patient.document, scheduleAt)
        }

        assertEquals(ErrorType.IMCOMPATIBLE_SCHEDULE, exception.errorType)
        assertEquals("Time incompatible with the doctor's schedule", exception.message)
    }

    @Test
    fun `should throw HealthMedException if doctor has another appointment at the same time`() {
        val scheduleAt = LocalDateTime.of(2024, 7, 26, 10, 0)
        val endTime = scheduleAt.plusMinutes(ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES.toLong())
        every { doctorGateway.get(doctor.crm) } returns doctor
        every { patientGateway.get(patient.document) } returns patient
        every { medicalAppointmentGateway.findAppointmentsByTimeAndDoctor(doctor.crm, scheduleAt, endTime) } returns listOf(medicalAppointment)

        val exception = assertThrows<HealthMedException> {
            medicalAppointmentService.schedule(doctor.crm, patient.document, scheduleAt)
        }

        assertEquals(ErrorType.UNAVAILABLE_TIME, exception.errorType)
        assertEquals("Time unavailable because the doctor already has another appointment", exception.message)
    }
}
