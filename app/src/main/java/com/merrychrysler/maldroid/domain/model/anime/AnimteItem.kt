package com.merrychrysler.maldroid.domain.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimteItem(
    val id: Int,
    val title: String,
    @SerialName("main_picture")
    val mainPicture: MainPicture? = null,
    @SerialName("alternative_titles")
    val alternativeTitles: AlternativeTitles? = null,
    @SerialName("start_date")
    val startDate: String? = null,
    @SerialName("end_date")
    val endDate: String? = null,
    val synopsis: String? = null,
    val mean: Float? = null,
    val rank: Int? = null,
    val popularity: Int? = null,
    @SerialName("num_list_users")
    val numListUsers: Int,
    @SerialName("num_scoring_users")
    val numScoringUsers: Int,
    val nsfw: String? = null,
    val genres: List<Genre>,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("media_type")
    val mediaType: String,
    val status: String,
    @SerialName("my_list_status")
    val myListStatus: ListStatus? = null,
    @SerialName("num_episodes")
    val numEpisodes: Int,
    @SerialName("start_season")
    val startSeason: StartSeason? = null,
    val broadcast: Broadcast? = null,
    val source: String? = null,
    @SerialName("average_episode_duration")
    val averageEpisodeDuration: Int? = null,
    val rating: String? = null,
    val studios: List<AnimeStudio>
)

