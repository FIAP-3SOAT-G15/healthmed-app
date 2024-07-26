package com.fiap.healthmed.adapter.gateway.config

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.adapter.gateway.MedicalAppointmentGateway
import com.fiap.healthmed.adapter.gateway.MedicalRecordGateway
import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.adapter.gateway.impl.*
import com.fiap.healthmed.driver.database.persistence.jpa.DoctorJpaRepository
import com.fiap.healthmed.driver.database.persistence.jpa.MedicalAppointmentJpaRepository
import com.fiap.healthmed.driver.database.persistence.jpa.MedicalRecordJpaRepository
import com.fiap.healthmed.driver.database.persistence.jpa.PatientJpaRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfig {

    @Bean
    fun createDoctorGateway(doctorJpaRepository: DoctorJpaRepository): DoctorGateway {
        return DoctorGatewayImpl(doctorJpaRepository)
    }

    @Bean
    fun createMedicalAppointmentGateway(medicalAppointJpaRepository: MedicalAppointmentJpaRepository): MedicalAppointmentGateway {
        return MedicalAppointmentGatewayImpl(medicalAppointJpaRepository)
    }

    @Bean
    fun createPatientGateway(patientJpaRepository: PatientJpaRepository): PatientGateway {
        return PatientGatewayImpl(patientJpaRepository)
    }

    @Bean
    fun createMedicalRecord(medicalJpaRepository: MedicalRecordJpaRepository, fileProvider: FileProvider): MedicalRecordGateway {
        return MedicalRecordGatewayImpl(medicalJpaRepository, fileProvider)
    }

}
