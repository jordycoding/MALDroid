package com.merrychrysler.maldroid.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class UserPreferencesRepository constructor(private val dataStore: DataStore<Preferences>) {
    data class UserPreferences(
        val accessToken: String,
        val accessTokenExpiry: String,
        val refreshToken: String
    )

    private object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val ACCESS_TOKEN_EXPIRY = stringPreferencesKey("access_token_expiry")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data.map { preferences ->
        val accessToken = preferences[PreferencesKeys.ACCESS_TOKEN] ?: ""
        val accessTokenExpiry = preferences[PreferencesKeys.ACCESS_TOKEN_EXPIRY] ?: ""
        val refreshToken = preferences[PreferencesKeys.REFRESH_TOKEN] ?: ""
        UserPreferences(accessToken, accessTokenExpiry, refreshToken)
    }

    suspend fun saveLoginInfo(
        accessToken: String,
        accessTokenExpiry: String,
        refreshToken: String
    ) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ACCESS_TOKEN] = accessToken
            preferences[PreferencesKeys.ACCESS_TOKEN_EXPIRY] = accessTokenExpiry
            preferences[PreferencesKeys.REFRESH_TOKEN] = refreshToken
        }
    }
}