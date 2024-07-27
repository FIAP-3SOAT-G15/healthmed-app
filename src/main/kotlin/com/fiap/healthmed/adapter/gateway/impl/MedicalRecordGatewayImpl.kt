package com.fiap.healthmed.adapter.gateway.impl

import com.fiap.healthmed.adapter.gateway.MedicalRecordGateway
import com.fiap.healthmed.domain.MedicalRecord
import com.fiap.healthmed.domain.errors.ErrorType
import com.fiap.healthmed.domain.errors.HealthMedException
import com.fiap.healthmed.driver.database.persistence.jpa.MedicalRecordJpaRepository
import com.fiap.healthmed.driver.database.persistence.mapper.MedicalRecordMapper
import org.springframework.stereotype.Component

class MedicalRecordGatewayImpl(
    private val medicalRecordJpaRepository: MedicalRecordJpaRepository,
    private val fileProvider: FileProvider) : MedicalRecordGateway {


    override fun sendFileProvider(record: MedicalRecord): MedicalRecord {
        record.content?.let {
            fileProvider.sendFile(
                path = record.fileLocation,
                content = it.toByteArray()
            )
        } ?: HealthMedException(
            message = "File with no content ",
            errorType = ErrorType.NO_MEDICAL_RECORDS
        )
        return record
    }

    override fun persistMetadata(record: MedicalRecord): MedicalRecord {
        return medicalRecordJpaRepository.save(MedicalRecordMapper.fromDomain(record))
            .let(MedicalRecordMapper::toDomain)
    }
}

interface FileProvider {

    fun sendFile(path: String, content: ByteArray)

}

@Component
class S3Provider : FileProvider {

    override fun sendFile(path: String, content: ByteArray) {
        Thread.sleep(500);
    }
}
