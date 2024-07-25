package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.MedicalAppointment

interface CancelAppointmentUseCase {

    fun cancel(
        documentPatient: String, appointmentNumber: String, justification: String
    ): MedicalAppointment
}
