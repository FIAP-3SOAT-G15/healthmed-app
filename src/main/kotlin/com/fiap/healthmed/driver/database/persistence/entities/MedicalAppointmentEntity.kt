package com.fiap.healthmed.driver.database.persistence.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "medical_appointment")
class MedicalAppointmentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_appointment_number")
    val number: Long?,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medical_appointment_doctor_crm")
    val doctor: DoctorEntity,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medical_appointment_patient_document")
    val patient: PatientEntity,
    @Column(name = "medical_appointment_expected_start_time")
    val expectedStartTime: LocalDateTime,
    @Column(name = "medical_appointment_estimated_time_spent_in_minutes")
    val estimatedTimeSpentInMinutes: Int,
    @Column(name = "medical_appointment_status")
    val status: String,
    @Column(name = "medical_appointment_status_changed")
    val statusChangedAt: LocalDateTime?,
    @Column(name = "medical_appointment_justification_cancellation_by_patient")
    val justificationCancellationByPatient: String?
)
