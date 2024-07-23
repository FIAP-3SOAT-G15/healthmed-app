package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.Patient

interface UpdatePatientUseCase {
    fun update(patient: Patient) : Patient
}