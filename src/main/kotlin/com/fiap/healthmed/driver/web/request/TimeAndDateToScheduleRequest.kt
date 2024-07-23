package com.fiap.healthmed.driver.web.request

import java.time.LocalDateTime

data class TimeAndDateToScheduleRequest(
    val scheduleAt: LocalDateTime,
)
