package com.fiap.healthmed.driver.web.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.fiap.healthmed.domain.AvailableTimes
import com.fiap.healthmed.driver.web.request.AvailableTimesRequest.AvailablePeriodsRequest
import io.swagger.v3.oas.annotations.media.Schema
import java.time.DayOfWeek


data class AvailableTimesRequest(
    @Schema(
        description = "Available slots", example = """
        {
            "MONDAY": [
                {
                    "start": "2022-12-31T00:00:00",
                    "end": "2022-12-31T23:59:59"
                }
            ]
        }
    """
    )
    val slots: Map<DayOfWeek, List<AvailablePeriodsRequest>>
) {
    data class AvailablePeriodsRequest(

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        @Schema(description = "Start time", example = "12:00")
        val start: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        @Schema(description = "End time", example = "18:00")
        val end: String
    )
}

fun AvailablePeriodsRequest.toDomain() = AvailableTimes.AvailablePeriods(
    start = start,
    end = end,
)

fun AvailableTimesRequest.toDomain() = AvailableTimes(
    slots = slots.mapValues { entry -> entry.value.map { it.toDomain() } }
)
