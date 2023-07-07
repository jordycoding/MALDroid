package com.merrychrysler.maldroid.domain.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAnimeListEntry(
    val node: AnimeItem,
    @SerialName("list_status")
    val listStatus: ListStatus? = null
)