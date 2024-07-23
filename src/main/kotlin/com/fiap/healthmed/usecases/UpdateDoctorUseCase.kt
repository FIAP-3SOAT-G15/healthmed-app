package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.Doctor

interface UpdateDoctorUseCase {
    fun update(doctor: Doctor): Doctor
}