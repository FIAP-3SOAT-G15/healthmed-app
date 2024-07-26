package com.fiap.healthmed.adapter.controller.config

import com.fiap.healthmed.domain.errors.ErrorType
import com.fiap.healthmed.domain.errors.HealthMedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(HealthMedException::class)
    protected fun domainErrorHandler(domainException: HealthMedException): ResponseEntity<ApiError> {
        val apiErrorResponseEntity: ApiErrorResponseEntity =
            when (domainException.errorType) {
                ErrorType.APPOINTMENT_NOT_FOUND,
                ErrorType.DOCTOR_NOT_FOUND,
                ErrorType.PATIENT_NOT_FOUND ->
                    ApiErrorResponseEntity(
                        ApiError(domainException.errorType.name, domainException.message),
                        HttpStatus.NOT_FOUND,
                    )

                ErrorType.PATIENT_ALREADY_EXISTS,
                ErrorType.DOCKER_ALREADY_EXISTS,
                ErrorType.IMCOMPATIBLE_SCHEDULE,
                ErrorType.UNAVAILABLE_TIME,
                ErrorType.NO_MEDICAL_RECORDS,
                ErrorType.INVALID_SLOT_TIME ->
                    ApiErrorResponseEntity(
                        ApiError(domainException.errorType.name, domainException.message),
                        HttpStatus.UNPROCESSABLE_ENTITY,
                    )

                else ->
                    ApiErrorResponseEntity(
                        ApiError(ErrorType.UNEXPECTED_ERROR.name, domainException.localizedMessage),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                    )
            }
        return ResponseEntity.status(apiErrorResponseEntity.status).body(apiErrorResponseEntity.body)
    }

    data class ApiError(val error: String, val message: String?)

    data class ApiErrorResponseEntity(val body: ApiError, val status: HttpStatus)
}
