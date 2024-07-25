package com.fiap.healthmed.adapter.gateway

import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.domain.Doctor

interface DoctorGateway {

    fun createDoctor(doctor: Doctor): Doctor

    fun updateDoctor(doctor: Doctor): Doctor

    fun updateDoctorAvailableTimes(crm: String, availableTimes: AvailableTimes): Doctor

    fun searchDoctors(query: Map<String, String>): List<Doctor>
}
