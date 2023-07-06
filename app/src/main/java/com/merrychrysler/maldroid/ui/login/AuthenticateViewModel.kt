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
import com.merrychrysler.maldroid.data.repository.MalRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val malRepository: MalRepositoryImpl
) : ViewModel() {
    val code: String = checkNotNull(savedStateHandle["code"])

    fun getAccessToken() {
        viewModelScope.launch {
            val token = malRepository.getToken(code)?.accessToken
            Log.d("AUTH", token ?: "no token")
        }
    }
}