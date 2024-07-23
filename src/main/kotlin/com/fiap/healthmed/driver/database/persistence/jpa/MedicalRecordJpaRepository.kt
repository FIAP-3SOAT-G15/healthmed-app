package com.fiap.healthmed.driver.database.persistence.jpa

import com.fiap.healthmed.driver.database.persistence.entities.MedicalRecordEntity
import org.springframework.data.repository.CrudRepository

interface MedicalRecordJpaRepository : CrudRepository<MedicalRecordEntity, Long> {
}
