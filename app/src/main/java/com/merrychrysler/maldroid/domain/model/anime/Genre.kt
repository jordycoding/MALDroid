package com.merrychrysler.maldroid.domain.model.anime

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String
)