package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.MedicalAppointment

interface ListMedicalAppointmentByDoctorUseCase {
    fun listByDoctor(crm: String): List<MedicalAppointment>
}
