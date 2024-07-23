package com.fiap.healthmed.driver.database.persistence.jpa

import com.fiap.healthmed.domain.MedicalAppointment
import org.springframework.data.repository.CrudRepository

interface MedicalAppointmentJpaRepository : CrudRepository<MedicalAppointment, Long> {
}
