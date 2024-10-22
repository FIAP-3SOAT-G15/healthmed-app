package com.fiap.healthmed.it

import com.fiap.healthmed.driver.web.request.AvailableTimesRequest
import com.fiap.healthmed.driver.web.request.DoctorRequest
import com.fiap.healthmed.driver.web.request.PatientRequest
import java.math.BigDecimal
import java.time.DayOfWeek

fun createAvailableTimesRequest(): AvailableTimesRequest {
    val availablePeriods = AvailableTimesRequest.AvailablePeriodsRequest(
        start = "09:00",
        end = "17:00"
    )

    return AvailableTimesRequest(
        mapOf(
            DayOfWeek.MONDAY to listOf(availablePeriods),
            DayOfWeek.TUESDAY to listOf(availablePeriods),
            DayOfWeek.WEDNESDAY to listOf(availablePeriods),
            DayOfWeek.THURSDAY to listOf(availablePeriods),
            DayOfWeek.FRIDAY to listOf(availablePeriods),
        )
    )
}

fun createDoctorRequest(
    crm: String = "111.222",
    document: String = "111.222.333-44",
    specialty: String = "Cardiologia",
    name: String = "Dr. Fulano de Tal",
    email: String = "fulanodetal@example.com",
    phoneNumber: String = "999999999",
    serviceZipCode: String = "01538-001",
    serviceAddress: String = "Av. Paulista, 1106",
    availableTimes: AvailableTimesRequest = createAvailableTimesRequest(),
    appointmentPrice: BigDecimal = BigDecimal("150.00"),
    password: String = "123456",
) = DoctorRequest(
    crm = crm,
    document = document,
    specialty = specialty,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    serviceZipCode = serviceZipCode,
    serviceAddress = serviceAddress,
    availableTimes = availableTimes,
    appointmentPrice = appointmentPrice,
    password = password,
)

fun createPatientRequest(
    document: String = "999.888.777-66",
    name: String = "Silvio Santos",
    email: String = "silviosantos@exemplo.com",
    phoneNumber: String = "988888888",
    address: String = "Av. Lins de Vasconcelos, 1222",
    zipCode: String = "01538-001",
    password: String = "123456",
) = PatientRequest(
    document = document,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    zipCode = zipCode,
    address = address,
    password = password,
)
