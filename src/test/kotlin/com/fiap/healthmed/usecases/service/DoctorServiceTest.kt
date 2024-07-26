package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.createDoctor
import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.domain.AvailableTimes.AvailablePeriods
import com.fiap.healthmed.domain.Doctor
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDateTime

class DoctorServiceTest {

    private lateinit var doctorGateway: DoctorGateway
    private lateinit var doctorService: DoctorService

    @BeforeEach
    fun setUp() {
        doctorGateway = mockk()
        doctorService = DoctorService(doctorGateway)
    }

    private val doctor = createDoctor()

    @Test
    fun `should create a doctor`() {
        every { doctorGateway.createDoctor(doctor) } returns doctor

        val result = doctorService.create(doctor)

        assertEquals(doctor, result)
        verify { doctorGateway.createDoctor(doctor) }
    }

    @Test
    fun `should update a doctor`() {
        every { doctorGateway.updateDoctor(doctor) } returns doctor

        val result = doctorService.update(doctor)

        assertEquals(doctor, result)
        verify { doctorGateway.updateDoctor(doctor) }
    }

    @Test
    fun `should update available times for a doctor`() {
        val crm = "111.222"
        val newAvailableTimes = AvailableTimes(
            mapOf(
                DayOfWeek.TUESDAY to listOf(
                    AvailablePeriods(
                        start = LocalDateTime.of(2024, 7, 26, 9, 0),
                        end = LocalDateTime.of(2024, 7, 26, 17, 0)
                    )
                )
            )
        )
        val updatedDoctor = doctor.copy(availableTimes = newAvailableTimes)
        every { doctorGateway.updateDoctorAvailableTimes(crm, newAvailableTimes) } returns updatedDoctor

        val result = doctorService.updateAvailableTime(crm, newAvailableTimes)

        assertEquals(updatedDoctor, result)
        verify { doctorGateway.updateDoctorAvailableTimes(crm, newAvailableTimes) }
    }

    @Test
    fun `should search doctor by name and speciality`() {
        val query = mapOf("speciality" to "Cardiologia", "name" to "Fulano de Tal")
        val doctors = listOf(doctor)
        every { doctorGateway.searchDoctorWithNameAndSpeciality("Cardiologia", "Fulano de Tal") } returns doctors

        val result = doctorService.search(query)

        assertEquals(doctors, result)
        verify { doctorGateway.searchDoctorWithNameAndSpeciality("Cardiologia", "Fulano de Tal") }
    }

    @Test
    fun `should search doctor by speciality`() {
        val query = mapOf("speciality" to "Cardiologia")
        val doctors = listOf(doctor)
        every { doctorGateway.searchDoctorWithSpeciality("Cardiologia") } returns doctors

        val result = doctorService.search(query)

        assertEquals(doctors, result)
        verify { doctorGateway.searchDoctorWithSpeciality("Cardiologia") }
    }

    @Test
    fun `should search doctor by name`() {
        val query = mapOf("name" to "Fulano de Tal")
        val doctors = listOf(doctor)
        every { doctorGateway.searchDoctorWithName("Fulano de Tal") } returns doctors

        val result = doctorService.search(query)

        assertEquals(doctors, result)
        verify { doctorGateway.searchDoctorWithName("Fulano de Tal") }
    }

    @Test
    fun `should return empty list when search query is empty`() {
        val query = emptyMap<String, String>()
        every { doctorGateway.searchDoctorWithName(any()) } returns emptyList()
        every { doctorGateway.searchDoctorWithSpeciality(any()) } returns emptyList()
        every { doctorGateway.searchDoctorWithNameAndSpeciality(any(), any()) } returns emptyList()

        val result = doctorService.search(query)

        assertEquals(emptyList<Doctor>(), result)
    }

    @Test
    fun `should get doctor by crm`() {
        val crm = "111.222"
        every { doctorGateway.get(crm) } returns doctor

        val result = doctorService.get(crm)

        assertEquals(doctor, result)
        verify { doctorGateway.get(crm) }
    }
}

