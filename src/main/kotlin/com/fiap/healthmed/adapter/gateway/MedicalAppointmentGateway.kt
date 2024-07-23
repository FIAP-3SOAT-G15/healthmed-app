package com.fiap.healthmed.adapter.gateway

import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.domain.MedicalAppointmentStatus
import java.time.LocalDateTime

interface MedicalAppointmentGateway {
    fun cancelAppointment(appointmentNumber: String, justification: String): MedicalAppointment

    fun updateAppointmentStatus(
        appointmentNumber: String,
        appointmentStatus: MedicalAppointmentStatus,
        justification: String = ""
    ): MedicalAppointment

    fun findAppointment(appointmentNumber: String): MedicalAppointment

    fun acceptAppointment(appointmentNumber: String): MedicalAppointment

    fun rejectAppointment(appointmentNumber: String): MedicalAppointment

    fun createAppointment(crm: String, document: String, scheduleAt: LocalDateTime): MedicalAppointment
}
