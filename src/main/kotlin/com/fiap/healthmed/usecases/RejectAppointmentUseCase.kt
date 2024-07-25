package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.MedicalAppointment

interface RejectAppointmentUseCase {
    fun reject(crm: String, appointmentNumber: String): MedicalAppointment
}
