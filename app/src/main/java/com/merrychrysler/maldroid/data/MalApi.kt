package com.merrychrysler.maldroid.data

import com.merrychrysler.maldroid.BuildConfig
import com.merrychrysler.maldroid.domain.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MalApi {
    @FormUrlEncoded
    @POST("v1/oauth2/token")
    suspend fun getAccessToken(
        @Field("code") code: String,
        @Field("code_verifier") codeVerifier: String,
        @Field("client_id") clientId: String = BuildConfig.MAL_CLIENT_ID,
        @Field("grant_type") grantType: String = "authorization_code"
    ): Response<TokenResponse>
}