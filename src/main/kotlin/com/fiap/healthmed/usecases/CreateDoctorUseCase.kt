package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.Doctor

interface CreateDoctorUseCase {
    fun create(doctor: Doctor): Doctor
}