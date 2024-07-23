package com.fiap.healthmed.driver.database.persistence.jpa

import com.fiap.healthmed.driver.database.persistence.entities.DoctorEntity
import org.springframework.data.repository.CrudRepository

interface DoctorJpaRepository : CrudRepository<DoctorEntity, String> {
}
