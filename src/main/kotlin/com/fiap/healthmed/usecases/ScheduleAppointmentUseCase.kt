package com.fiap.healthmed.usecases

import com.fiap.healthmed.domain.MedicalAppointment
import java.time.LocalDateTime

interface ScheduleAppointmentUseCase {
    fun schedule(crm: String, document: String, scheduleAt: LocalDateTime): MedicalAppointment
}
