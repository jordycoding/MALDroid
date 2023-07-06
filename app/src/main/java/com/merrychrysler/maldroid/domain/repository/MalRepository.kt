package com.merrychrysler.maldroid.domain.repository

import com.merrychrysler.maldroid.domain.model.TokenResponse
import com.merrychrysler.maldroid.domain.model.anime.UserAnimeList

interface MalRepository {
   suspend fun getToken(code: String): TokenResponse?
   suspend fun getAnimeList(): UserAnimeList?
   fun setCodeVerifier(codeVerifier: String)
}