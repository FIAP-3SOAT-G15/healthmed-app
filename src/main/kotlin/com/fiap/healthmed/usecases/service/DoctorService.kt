package com.fiap.healthmed.usecases.service

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.usecases.CreateDoctorUseCase
import com.fiap.healthmed.usecases.SearchDoctorUseCase
import com.fiap.healthmed.usecases.UpdateAvailableTimeDoctorUseCase
import com.fiap.healthmed.usecases.UpdateDoctorUseCase

class DoctorService(
    private val doctorGateway: DoctorGateway
) : CreateDoctorUseCase, UpdateDoctorUseCase, UpdateAvailableTimeDoctorUseCase, SearchDoctorUseCase {

    override fun create(doctor: Doctor): Doctor {
        return doctorGateway.createDoctor(doctor)
    }

    override fun update(doctor: Doctor): Doctor {
        return doctorGateway.updateDoctor(doctor)
    }

    override fun updateAvailableTime(crm: String, availableTimes: AvailableTimes): Doctor {
        return doctorGateway.updateDoctorAvailableTimes(crm, availableTimes)
    }

    override fun search(query: Map<String, String>): List<Doctor> {
        return doctorGateway.searchDoctors(query)
    }
}
