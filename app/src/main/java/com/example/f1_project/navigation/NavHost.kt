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
    isDarkTheme: Boolean, // Estado do tema passado para navegação
    onThemeChange: (Boolean) -> Unit // Função para alterar o tema
) {
    NavHost(navController = navController, startDestination = "pilots") {
        composable("pilots") {
            // Chama a MainScreen que já inclui a PilotsScreen e a BottomBar
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
            // Chama a tela de perfil
            ProfileScreen()
        }
        composable("settings") {
            // Passa o estado do tema e a função para alterar o tema
            SettingsScreen(isDarkTheme = isDarkTheme, onThemeChange = onThemeChange)
        }
    }
}
