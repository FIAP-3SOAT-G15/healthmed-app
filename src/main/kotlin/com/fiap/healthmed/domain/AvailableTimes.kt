package com.fiap.healthmed.domain

import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime

data class AvailableTimes(val slots: Map<DayOfWeek, List<AvailablePeriods>>) {

    data class AvailablePeriods(val start: LocalDateTime, val end: LocalDateTime) {
        init {
            if (start.isAfter(end)
                || Duration.between(start, end) < Duration.ofMinutes(50)
                || Duration.between(start, end) > Duration.ofDays(1)
            ) {
                throw IllegalArgumentException("start must be after the end")
            }
        }
    }
}
