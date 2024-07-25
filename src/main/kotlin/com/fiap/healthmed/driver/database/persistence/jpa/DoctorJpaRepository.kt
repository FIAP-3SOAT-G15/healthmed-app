package com.fiap.healthmed.driver.database.persistence.jpa

import com.fiap.healthmed.driver.database.persistence.entities.DoctorEntity
import org.springframework.data.repository.CrudRepository

interface DoctorJpaRepository : CrudRepository<DoctorEntity, String> {
    fun findByCrm(crm: String): DoctorEntity?
    fun findBySpecialty(speciality: String): List<DoctorEntity>
    fun findByNameContains(name: String): List<DoctorEntity>
    fun findByNameContainsAndSpecialty(speciality: String, name: String): List<DoctorEntity>
}
