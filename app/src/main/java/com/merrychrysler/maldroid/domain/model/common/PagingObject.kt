package com.merrychrysler.maldroid.domain.model.common

import kotlinx.serialization.Serializable

@Serializable
data class PagingObject(
    val previous: String,
    val next: String
)