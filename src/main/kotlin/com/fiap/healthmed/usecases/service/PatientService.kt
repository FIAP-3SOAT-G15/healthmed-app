package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.domain.Patient
import com.fiap.healthmed.usecases.CreatePatientUseCase
import com.fiap.healthmed.usecases.UpdatePatientUseCase

class PatientService(private val patientGateway: PatientGateway) : CreatePatientUseCase, UpdatePatientUseCase {

    override fun create(patient: Patient): Patient {
        return patientGateway.createPatient(patient)
    }

    override fun update(patient: Patient): Patient {
        return patientGateway.updatePatient(patient)
    }
}
