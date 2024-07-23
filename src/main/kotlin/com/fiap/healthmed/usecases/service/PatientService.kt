package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.domain.Patient
import com.fiap.healthmed.usecases.CreatePatientUseCase
import com.fiap.healthmed.usecases.UpdatePatientUseCase

class PatientService(val patientGateway: PatientGateway): CreatePatientUseCase, UpdatePatientUseCase {
    override fun create(patient: Patient): Patient {
        TODO("Not yet implemented")
    }

    override fun update(patient: Patient): Patient {
        TODO("Not yet implemented")
    }
}
