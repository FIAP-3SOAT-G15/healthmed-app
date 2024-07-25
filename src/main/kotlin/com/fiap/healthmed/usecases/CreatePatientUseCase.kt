package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.Patient

interface CreatePatientUseCase {
    fun create(patient: Patient): Patient
}
