package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.driver.database.persistence.jpa.MedicalAppointmentJpaRepository

class MedicalAppointmentGatewayImpl(private val medicalAppointmentJpaRepository: MedicalAppointmentJpaRepository) : MedicalAppointmentGateway {
}
