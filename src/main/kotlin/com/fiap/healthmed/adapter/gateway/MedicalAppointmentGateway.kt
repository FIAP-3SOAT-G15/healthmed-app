package com.fiap.healthmed.adapter.gateway

import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.domain.MedicalAppointmentStatus
import com.fiap.healthmed.domain.Patient
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

    fun createAppointment(doctor: Doctor, patient: Patient, scheduleAt: LocalDateTime): MedicalAppointment

    fun findAppointmentsByPatient(patientDocument: String): List<MedicalAppointment>

    fun findAppointmentsByDoctor(crm: String): List<MedicalAppointment>

    fun findAppointmentsByTimeAndDoctor(crm: String, startTime: LocalDateTime, endTime: LocalDateTime): List<MedicalAppointment>
}
