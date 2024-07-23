package com.fiap.healthmed.driver.database.persistence.jpa

import com.fiap.healthmed.driver.database.persistence.entities.PatientEntity
import org.springframework.data.repository.CrudRepository

interface PatientJpaRepository : CrudRepository<PatientEntity, String> {
}
