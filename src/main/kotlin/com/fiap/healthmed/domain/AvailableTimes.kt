package com.fiap.healthmed.domain

import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime

data class AvailableTimes(val slots: Map<DayOfWeek, List<AvailablePeriods>>) {
    fun canAccept(scheduleAt: LocalDateTime): Boolean {
        val dayOfWeek = scheduleAt.dayOfWeek
        val startRequest = scheduleAt;
        val endRequest = scheduleAt.plusMinutes(ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES.toLong());

        val periods = slots[dayOfWeek] ?: emptyList()

        return periods.any { period ->

            (startRequest.isAfter(period.start) || startRequest.isEqual(period.start))
                && (endRequest.isBefore(period.end) || endRequest.isEqual(period.end))

        }

    }

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
