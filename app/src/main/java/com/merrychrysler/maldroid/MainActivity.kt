package com.merrychrysler.maldroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.merrychrysler.maldroid.ui.anime.AnimeMainView
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
                        composable("main") {
                            MainView()
                        }
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
            AuthenticateView(viewModel, navController)
        }
    }
}

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val activeIcon: ImageVector
) {
    object Anime : Screen("anime", R.string.anime, Icons.Outlined.Tv, Icons.Filled.Tv)
    object Manga : Screen("manga", R.string.manga, Icons.Outlined.Book, Icons.Filled.Book)
    object Profile : Screen(
        "profile",
        R.string.profile,
        Icons.Outlined.AccountCircle,
        Icons.Filled.AccountCircle
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(modifier: Modifier = Modifier) {
    val items = listOf(Screen.Anime, Screen.Manga, Screen.Profile)
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    val active =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    val icon = if (active) screen.activeIcon else screen.icon
                    NavigationBarItem(
                        icon = { Icon((icon), contentDescription = null) },
                        label = { Text(stringResource(id = screen.resourceId)) },
                        selected = active,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Anime.route,
            modifier.padding(innerPadding)
        ) {
            composable(Screen.Anime.route) { AnimeMainView() }
            composable(Screen.Manga.route) { Text("manga") }
            composable(Screen.Profile.route) { Text("profile") }
        }
    }
}