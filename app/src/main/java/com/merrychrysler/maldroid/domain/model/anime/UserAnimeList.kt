package com.merrychrysler.maldroid.domain.model.anime

import com.merrychrysler.maldroid.domain.model.common.PagingObject
import kotlinx.serialization.Serializable

@Serializable
data class UserAnimeList(
    val data: List<UserAnimeListEntry>,
    val paging: PagingObject
)
