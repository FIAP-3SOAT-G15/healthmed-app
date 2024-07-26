package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.adapter.gateway.MedicalRecordGateway
import com.fiap.healthmed.domain.MedicalAppointment
import com.fiap.healthmed.domain.MedicalAppointmentStatus
import com.fiap.healthmed.domain.MedicalRecord
import com.fiap.healthmed.usecases.AppendInMedicalRecordUseCase

private const val LOCATION_FILES = "/fake/path/to/medicalrecord"

class MedicalRecordService(
    private val medicalRecordGateway: MedicalRecordGateway,
    private val medicalAppointmentGateway: MedicalAppointmentGateway) : AppendInMedicalRecordUseCase {



    override fun append(content: String, appointmentNumber: String): MedicalAppointment {
        val appointment = medicalAppointmentGateway.findAppointment(appointmentNumber)

        val record = medicalRecordGateway.sendFileProvider(
            MedicalRecord(
                medicalAppointment = appointment,
                doctorCrm = appointment.doctor.crm,
                patientDocument = appointment.patientDocument.document,
                fileLocation = LOCATION_FILES,
                content = content,
                fileName = String.format("%s-%s.txt", appointmentNumber, appointment.doctor.crm)
            )
        )

        medicalRecordGateway.persistMetadata(
            record = record,
        )

        return medicalAppointmentGateway.updateAppointmentStatus(
            appointmentNumber = appointmentNumber,
            appointmentStatus = MedicalAppointmentStatus.FINISHED
        )
    }

}
