package com.merrychrysler.maldroid.domain.model.anime

import kotlinx.serialization.Serializable

@Serializable
data class AlternativeTitles(
    val synonyms: List<String>? = null,
    val en: String? = null,
    val ja: String? = null
)