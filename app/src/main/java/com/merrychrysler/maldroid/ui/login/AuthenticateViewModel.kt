package com.merrychrysler.maldroid.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.merrychrysler.maldroid.data.AuthInterceptor
import com.merrychrysler.maldroid.data.repository.MalRepositoryImpl
import com.merrychrysler.maldroid.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val malRepository: MalRepositoryImpl,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val authInterceptor: AuthInterceptor
) : ViewModel() {
    val code: String = checkNotNull(savedStateHandle["code"])

    fun getAccessToken(onFinished: () -> Unit) {
        viewModelScope.launch {
            val res = malRepository.getToken(code)
            if (res != null) {
                userPreferencesRepository.saveLoginInfo(res.accessToken, res.expiresIn.toString(), res.refreshToken)
                authInterceptor.setToken(res.accessToken)
            }
            onFinished()
        }
    }
}