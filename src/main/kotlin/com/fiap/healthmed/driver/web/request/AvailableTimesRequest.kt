package com.fiap.healthmed.driver.web.request

import java.time.DayOfWeek
import java.time.LocalDateTime

data class AvailableTimesRequest(val slots: Map<DayOfWeek, List<AvailablePeriodsRequest>>) {

    data class AvailablePeriodsRequest(val start: LocalDateTime, val end: LocalDateTime)
}
