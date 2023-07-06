package com.merrychrysler.maldroid.domain.model.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeStudio(
    val id: Int,
    val name: String
)