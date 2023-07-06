package com.merrychrysler.maldroid.data.repository

import com.merrychrysler.maldroid.data.MalApi
import com.merrychrysler.maldroid.domain.model.TokenResponse
import com.merrychrysler.maldroid.domain.repository.MalRepository

class MalRepositoryImpl(private val api: MalApi) : MalRepository {
    private var codeVerifier: String = ""

    override suspend fun getToken(code: String): TokenResponse? {
        return api.getAccessToken(code, this.codeVerifier).body()
    }

    override fun setCodeVerifier(codeVerifier: String) {
        this.codeVerifier = codeVerifier
    }
}