package com.fiap.healthmed.adapter.gateway

import com.fiap.healthmed.domain.Patient

interface PatientGateway {
    fun createPatient(patient: Patient): Patient

    fun updatePatient(patient: Patient): Patient

    fun get(document: String): Patient
}
