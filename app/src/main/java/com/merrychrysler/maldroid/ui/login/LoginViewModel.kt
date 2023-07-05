package com.merrychrysler.maldroid.ui.login

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModel
import com.merrychrysler.maldroid.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import java.security.SecureRandom
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    fun loginClick(context: Context) {
        val builder = Uri.Builder()
        builder.scheme("https").authority("myanimelist.net").appendPath("v1").appendPath("oauth2")
            .appendPath("authorize").appendQueryParameter("response_type", "code")
            .appendQueryParameter("client_id", BuildConfig.MAL_CLIENT_ID)
            .appendQueryParameter("code_challenge", generateCodeVerifier())

        val intent = CustomTabsIntent.Builder().build()
        intent.launchUrl(context, builder.build())
    }

    private fun generateCodeVerifier(): String {
        val secureRandom = SecureRandom()
        val codeVerifier = ByteArray(64)
        secureRandom.nextBytes(codeVerifier)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier)
    }
}