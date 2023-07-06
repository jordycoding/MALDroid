package com.merrychrysler.maldroid.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AuthenticateView(
    viewModel: AuthenticateViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.getAccessToken { navController.navigate("main") }
    }

    Column {
        Text(text = viewModel.code)
    }
}