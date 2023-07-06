package com.merrychrysler.maldroid.domain.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Broadcast(
    @SerialName("day_of_the_week")
    val dayOfWeek: String,
    @SerialName("start_time")
    val startTime: String? = null
)