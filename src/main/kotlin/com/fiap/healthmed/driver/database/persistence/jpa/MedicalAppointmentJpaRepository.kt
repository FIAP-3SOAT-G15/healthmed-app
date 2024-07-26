package com.fiap.healthmed.driver.database.persistence.jpa

import com.fiap.healthmed.driver.database.persistence.entities.MedicalAppointmentEntity
import org.springframework.data.repository.CrudRepository
import java.time.Instant
import java.time.LocalDateTime

interface MedicalAppointmentJpaRepository : CrudRepository<MedicalAppointmentEntity, Long> {

    fun findByDoctorCrmAndExpectedStartTimeBetween(
        crm: String,
        start: LocalDateTime,
        end: LocalDateTime
    ): List<MedicalAppointmentEntity>

    fun findByDoctorCrm(crm: String): List<MedicalAppointmentEntity>

    fun findByPatientDocument(document: String): List<MedicalAppointmentEntity>

}
