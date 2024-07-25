package com.fiap.healthmed.driver.database.persistence.jpa

import com.fiap.healthmed.driver.database.persistence.entities.MedicalAppointmentEntity
import org.springframework.data.repository.CrudRepository

interface MedicalAppointmentJpaRepository : CrudRepository<MedicalAppointmentEntity, Long> {
}
