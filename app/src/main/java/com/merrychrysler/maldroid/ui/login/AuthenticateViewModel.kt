package com.merrychrysler.maldroid.ui.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    val code: String = checkNotNull(savedStateHandle["code"])
}