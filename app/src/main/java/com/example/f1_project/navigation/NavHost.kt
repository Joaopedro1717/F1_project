package com.example.f1_project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.f1_project.functions.getPilots
import com.example.f1_project.screens.MainScreen
import com.example.f1_project.screens.PilotDetailsScreen
import com.example.f1_project.screens.ProfileScreen
import com.example.f1_project.screens.SettingsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = "pilots") {
        composable("pilots") {

            MainScreen(navController = navController)
        }
        composable("details/{pilotName}") { backStackEntry ->
            val pilotName = backStackEntry.arguments?.getString("pilotName")
            val pilot = getPilots().find { it.name == pilotName }
            pilot?.let {
                PilotDetailsScreen(pilot = it)
            }
        }
        composable("profile") {

            ProfileScreen()
        }
        composable("settings") {

            SettingsScreen(isDarkTheme = isDarkTheme, onThemeChange = onThemeChange)
        }
    }
}
