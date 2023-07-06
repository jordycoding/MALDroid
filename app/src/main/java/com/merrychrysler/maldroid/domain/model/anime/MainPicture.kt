package com.merrychrysler.maldroid.domain.model.anime

import kotlinx.serialization.Serializable

@Serializable
data class MainPicture(
    val large: String? = null,
    val medium: String
)