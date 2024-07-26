package com.fiap.healthmed.adapter.gateway

import com.fiap.healthmed.domain.Doctor

interface DoctorGateway {

    fun createDoctor(doctor: Doctor): Doctor

    fun updateDoctor(doctor: Doctor): Doctor

    fun get(crm: String): Doctor

    fun searchDoctorWithName(name: String): List<Doctor>

    fun searchDoctorWithSpeciality(speciality: String): List<Doctor>

    fun searchDoctorWithNameAndSpeciality(speciality: String, name: String): List<Doctor>
}
