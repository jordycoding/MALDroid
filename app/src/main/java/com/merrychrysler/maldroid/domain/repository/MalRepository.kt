package com.merrychrysler.maldroid.domain.repository

import com.merrychrysler.maldroid.domain.model.TokenResponse

interface MalRepository {
   suspend fun getToken(code: String): TokenResponse?
   fun setCodeVerifier(codeVerifier: String)
}