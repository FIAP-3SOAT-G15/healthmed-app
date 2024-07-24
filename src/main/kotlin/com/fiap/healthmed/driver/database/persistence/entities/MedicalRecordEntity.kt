package com.fiap.healthmed.driver.database.persistence.entities

import jakarta.persistence.*

@Entity
@Table(name = "medical_record")
class MedicalRecordEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_number")
    val number: Long?,
    @Column(name = "medical_record_medical_appointment_number")
    val medicalAppointmentNumber: Long,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medical_record_doctor_crm")
    val doctor: DoctorEntity? = null,
    @Column(name = "medical_record_patient_document")
    val patientDocument: String,
    @Column(name = "medical_record_provider")
    val provider: String,
    @Column(name = "medical_record_file_location")
    val fileLocation: String,
    @Column(name = "medical_record_file_name")
    val fileName: String,
)
