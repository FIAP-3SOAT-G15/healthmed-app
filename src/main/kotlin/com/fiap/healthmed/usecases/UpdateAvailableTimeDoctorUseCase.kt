package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.domain.Doctor

interface UpdateAvailableTimeDoctorUseCase {
    fun updateAvailableTime(crm: String, availableTimes: AvailableTimes): Doctor
}