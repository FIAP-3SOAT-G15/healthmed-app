package com.fiap.healthmed.driver.web.request

import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.driver.web.request.AvailableTimesRequest.AvailablePeriodsRequest
import java.time.DayOfWeek
import java.time.LocalDateTime

data class AvailableTimesRequest(val slots: Map<DayOfWeek, List<AvailablePeriodsRequest>>) {

    data class AvailablePeriodsRequest(val start: LocalDateTime, val end: LocalDateTime)
}

fun AvailablePeriodsRequest.toDomain() = AvailableTimes.AvailablePeriods(
    start = start, end = end,
)

fun AvailableTimesRequest.toDomain() = AvailableTimes(
    slots = slots.mapValues { entry -> entry.value.map { it.toDomain() } }
)
