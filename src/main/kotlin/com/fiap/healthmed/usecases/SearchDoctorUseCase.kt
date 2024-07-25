package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.Doctor

interface SearchDoctorUseCase {
    fun search(query: Map<String, String>): List<Doctor>
    fun get(crm: String): Doctor
}
