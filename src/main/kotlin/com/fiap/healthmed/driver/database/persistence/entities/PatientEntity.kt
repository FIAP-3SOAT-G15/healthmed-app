package com.fiap.healthmed.driver.database.persistence.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "patient")
class PatientEntity(
    @Id
    @Column(name = "patient_document")
    val document: String,
    @Column(name = "patient_name")
    val name: String,
    @Column(name = "patient_email")
    val email: String,
    @Column(name = "patient_phone")
    val phoneNumber: String,
    @Column(name = "patient_zip_code")
    val zipCode: String,
    @Column(name = "patient_address")
    val address: String
)
