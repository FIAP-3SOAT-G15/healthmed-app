package com.fiap.healthmed.domain

import com.fiap.healthmed.domain.errors.ErrorType
import com.fiap.healthmed.domain.errors.HealthMedException
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime

data class AvailableTimes(val slots: Map<DayOfWeek, List<AvailablePeriods>> = emptyMap()) {
    fun canAccept(scheduleAt: LocalDateTime): Boolean {
        val dayOfWeek = scheduleAt.dayOfWeek
        val startRequest = scheduleAt;
        val endRequest = scheduleAt.plusMinutes(ESTIMATED_MEDICAL_APPOINTMENT_DURATION_IN_MINUTES.toLong());

        val periods = slots[dayOfWeek] ?: emptyList()

        return periods.any { period ->

                val startTime = createLocalDateTime(period.start, startRequest)
                val endTime = createLocalDateTime(period.end, startRequest)


                return (startRequest.isAfter(startTime) || startRequest.isEqual(startTime))
                    && (endRequest.isBefore(endTime) || endRequest.isEqual(endTime))
            }


    }

    data class AvailablePeriods(val start: String = "08:00", val end: String = "17:00") {
        init {
            val now = LocalDateTime.now()
            val startTime = createLocalDateTime(start, now)
            val endTime = createLocalDateTime(end, now)

            if (startTime.isAfter(endTime)
                || Duration.between(startTime, endTime) < Duration.ofMinutes(50)
                || Duration.between(startTime, endTime) > Duration.ofDays(1)
            ) {
                throw HealthMedException(
                    errorType = ErrorType.INVALID_SLOT_TIME,
                    message = "Invalid slots start or end time"
                )
            }
        }
    }

}

fun createLocalDateTime(time: String, now: LocalDateTime): LocalDateTime {
    val timeSplit = time.split(":")
    return now.withHour(timeSplit[0].toInt()).withMinute(timeSplit[1].toInt())
}
