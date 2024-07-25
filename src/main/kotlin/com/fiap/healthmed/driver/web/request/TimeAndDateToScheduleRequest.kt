package com.fiap.healthmed.driver.web.request

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class TimeAndDateToScheduleRequest(
    @Schema(description = "scheduleAt", example = "2022-12-31T23:59:59")
    val scheduleAt: LocalDateTime,
)
