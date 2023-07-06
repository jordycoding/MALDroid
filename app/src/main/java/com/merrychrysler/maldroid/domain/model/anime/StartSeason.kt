package com.merrychrysler.maldroid.domain.model.anime

import kotlinx.serialization.Serializable

@Serializable
data class StartSeason(
    val year: Int,
    val season: String
)