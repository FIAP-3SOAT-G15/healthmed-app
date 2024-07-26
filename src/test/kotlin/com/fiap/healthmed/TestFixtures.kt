package com.fiap.healthmed

import com.fiap.healthmed.domain.*
import com.fiap.healthmed.domain.AvailableTimes.AvailablePeriods
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

fun createAvailableTimes(): AvailableTimes {
    val availablePeriods = AvailablePeriods(
        start = "09:00",
        end = "17:00"
    )

    return AvailableTimes(
        mapOf(
            DayOfWeek.MONDAY to listOf(availablePeriods),
            DayOfWeek.TUESDAY to listOf(availablePeriods),
            DayOfWeek.WEDNESDAY to listOf(availablePeriods),
            DayOfWeek.THURSDAY to listOf(availablePeriods),
            DayOfWeek.FRIDAY to listOf(availablePeriods),
        )
    )
}

fun createDoctor(
    crm: String = "111.222",
    document: String = "111.222.333-44",
    specialty: String = "Cardiologia",
    name: String = "Dr. Fulano de Tal",
    email: String = "fulanodetal@example.com",
    phoneNumber: String = "999999999",
    serviceZipCode: String = "01538-001",
    serviceAddress: String = "Av. Paulista, 1106",
    availableTimes: AvailableTimes = createAvailableTimes(),
    appointmentPrice: BigDecimal = BigDecimal("150.00")
) = Doctor(
    crm = crm,
    document = document,
    specialty = specialty,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    serviceZipCode = serviceZipCode,
    serviceAddress = serviceAddress,
    availableTimes = availableTimes,
    appointmentPrice = appointmentPrice
)

fun createPatient(
    document: String = "999.888.777-66",
    name: String = "Silvio Santos",
    email: String = "silviosantos@exemplo.com",
    phoneNumber: String = "988888888",
    address: String = "Av. Lins de Vasconcelos, 1222",
    zipCode: String = "01538-001"
) = Patient(
    document = document,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    address = address,
    zipCode = zipCode
)

fun createMedicalAppointment(
    number: Long? = null,
    doctor: Doctor = createDoctor(),
    patient: Patient = createPatient(),
    expectedStartTime: LocalDateTime = LocalDateTime.of(2024, 7, 24, 9, 0),
    estimatedTimeSpentInMinutes: Int = ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES,
    status: MedicalAppointmentStatus = MedicalAppointmentStatus.SCHEDULED,
    statusChangedAt: LocalDateTime = LocalDateTime.now(),
    justificationCancellationByPatient: String? = null
) = MedicalAppointment(
    number = number,
    doctor = doctor,
    patient = patient,
    expectedStartTime = expectedStartTime,
    estimatedTimeSpentInMinutes = estimatedTimeSpentInMinutes,
    status = status,
    statusChangedAt = statusChangedAt,
    justificationCancellationByPatient = justificationCancellationByPatient
)
