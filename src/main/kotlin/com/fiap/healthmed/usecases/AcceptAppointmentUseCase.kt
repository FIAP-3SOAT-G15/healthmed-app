package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.MedicalAppointment

interface AcceptAppointmentUseCase {
    fun accept(crm: String, appointmentNumber: String) : MedicalAppointment
}
