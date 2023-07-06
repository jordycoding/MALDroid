package com.merrychrysler.maldroid.domain.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListStatus(
    val status: String? = null,
    val score: Int,
    @SerialName("num_episodes_watched")
    val numEpisodesWatched: Int,
    @SerialName("is_rewatching")
    val isRewatching: Boolean,
    @SerialName("start_date")
    val startDate: String? = null,
    @SerialName("finish_date")
    val finishDate: String? = null,
    val priority: Int,
    @SerialName("num_times_rewatched")
    val numTimesRewatched: Int,
    @SerialName("rewatch_value")
    val rewatchValue: Int,
    val tags: List<String>,
    val comments: String,
    @SerialName("updated_at")
    val updatedAt: String
)