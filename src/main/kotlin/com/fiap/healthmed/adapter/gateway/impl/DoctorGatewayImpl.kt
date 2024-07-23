package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.driver.database.persistence.jpa.DoctorJpaRepository

class DoctorGatewayImpl(private val doctorJpaRepository: DoctorJpaRepository) : DoctorGateway {
}
