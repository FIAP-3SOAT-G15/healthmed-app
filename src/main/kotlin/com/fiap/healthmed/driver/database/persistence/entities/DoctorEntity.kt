package com.fiap.healthmed.driver.database.persistence.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "doctor")
class DoctorEntity(
    @Id
    @Column(name = "doctor_crm")
    val crm: String,
    @Column(name = "doctor_document")
    val document: String,
    @Column(name = "doctor_specialty")
    val specialty: String,
    @Column(name = "doctor_name")
    val name: String,
    @Column(name = "doctor_email")
    val email: String,
    @Column(name = "doctor_phone")
    val phoneNumber: String,
    @Column(name = "doctor_service_zip_code")
    val serviceZipCode: String,
    @Column(name = "doctor_service_address")
    val serviceAddress: String,
    @Column(name = "doctor_available_times")
    val doctorAvailableTimes: String,
    @Column(name = "doctor_appointment_price")
    val appointmentPrice: BigDecimal,
)
