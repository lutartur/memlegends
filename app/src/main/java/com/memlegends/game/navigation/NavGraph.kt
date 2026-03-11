package com.memlegends.game.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.memlegends.game.ui.screens.game.GameScreen
import com.memlegends.game.ui.screens.home.HomeScreen

private const val ROUTE_HOME = "home"
private const val ROUTE_GAME = "game"

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ROUTE_HOME,
    ) {
        composable(
            route = ROUTE_HOME,
            exitTransition = { slideOutVertically { -it } + fadeOut() },
            popEnterTransition = { slideInVertically { -it } + fadeIn() },
        ) {
            HomeScreen(onStartGame = { navController.navigate(ROUTE_GAME) })
        }

        composable(
            route = ROUTE_GAME,
            enterTransition = { slideInVertically { it } + fadeIn() },
            popExitTransition = { slideOutVertically { it } + fadeOut() },
        ) {
            GameScreen()
        }
    }
}
