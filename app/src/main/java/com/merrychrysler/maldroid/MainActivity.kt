package com.merrychrysler.maldroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.merrychrysler.maldroid.ui.login.AuthenticateView
import com.merrychrysler.maldroid.ui.login.AuthenticateViewModel
import com.merrychrysler.maldroid.ui.login.LoginView
import com.merrychrysler.maldroid.ui.login.LoginViewModel
import com.merrychrysler.maldroid.ui.theme.MALDroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MALDroidTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "login") {
                        loginGraph(navController)
                    }
                }
            }
        }
    }
}

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(startDestination = "start", route = "login") {
        composable("start") {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginView(viewModel)
        }
        composable(
            "authenticate?code={code}",
            deepLinks = listOf(
                navDeepLink { uriPattern = "maldroid://authenticate?code={code}" }
            ),
        ) {
            val viewModel = hiltViewModel<AuthenticateViewModel>()
            AuthenticateView(viewModel)
        }
    }
}