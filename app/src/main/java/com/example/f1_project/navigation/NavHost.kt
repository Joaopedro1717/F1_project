package com.example.f1_project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.f1_project.screens.MainScreen
import com.example.f1_project.screens.PilotDetailsScreen
import com.example.f1_project.screens.ProfileScreen
import com.example.f1_project.screens.SettingsScreen
import com.example.f1_project.screens.AddPilotScreen
import com.example.f1_project.data.models.Pilot
import com.example.f1_project.data.models.PilotViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onAddPilot: (Pilot) -> Unit // Corrigido para uma função normal
) {
    val pilotViewModel: PilotViewModel = viewModel() // Obtém o ViewModel
    val pilots = pilotViewModel.pilots.observeAsState(listOf()).value // Observa a lista de pilotos

    NavHost(navController = navController, startDestination = "pilots") {
        composable("pilots") {
            MainScreen(
                navController = navController,
                pilots = pilots, // Passa a lista de pilotos do ViewModel
                onAddPilot = onAddPilot // Passa a função onAddPilot normalmente
            )
        }
        composable("details/{pilotName}") { backStackEntry ->
            val pilotName = backStackEntry.arguments?.getString("pilotName")
            val pilot = pilots.find { it.name == pilotName }
            pilot?.let {
                PilotDetailsScreen(pilot = it)
            }
        }
        composable("profile") {
            ProfileScreen()
        }
        composable("settings") {
            SettingsScreen(
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }
        composable("addPilot") {
            AddPilotScreen(
                navController = navController,
                onAddPilot = onAddPilot // Passa a função normalmente
            )
        }
    }
}

