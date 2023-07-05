package com.merrychrysler.maldroid.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthenticateView(viewModel: AuthenticateViewModel, modifier: Modifier = Modifier) {
    Column {
       Text(text = viewModel.code) 
    }
}