package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.MedicalAppointment

interface ListMedicalAppointmentByPatientUseCase {
    fun listByPatient(patientDocument : String) : List<MedicalAppointment>
}
