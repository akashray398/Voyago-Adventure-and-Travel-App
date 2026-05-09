package com.akash.voyagotravel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akash.voyagotravel.ui.screens.*

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Detail : Screen("detail/{destinationId}") {
        fun createRoute(destinationId: String) = "detail/$destinationId"
    }
    object Tracking : Screen("tracking")
    object Booking : Screen("booking")
    object Favorites : Screen("favorites")
    object Profile : Screen("profile")
}

@Composable
fun VoyagoNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Onboarding.route) { OnboardingScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Detail.route) { backStackEntry ->
            val destinationId = backStackEntry.arguments?.getString("destinationId")
            DestinationDetailScreen(navController, destinationId)
        }
        composable(Screen.Tracking.route) { RouteTrackingScreen(navController) }
        composable(Screen.Booking.route) { BookingScreen(navController) }
        composable(Screen.Favorites.route) { FavoritesScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
    }
}
