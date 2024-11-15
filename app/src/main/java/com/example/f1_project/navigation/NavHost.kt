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
import com.example.f1_project.screens.EditPilotScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onAddPilot: (Pilot) -> Unit
) {
    val pilotViewModel: PilotViewModel = viewModel()
    val pilots = pilotViewModel.pilots.observeAsState(listOf()).value

    NavHost(navController = navController, startDestination = "pilots") {
        composable("pilots") {
            MainScreen(
                navController = navController,
                pilots = pilots,
                onAddPilot = onAddPilot
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
                onAddPilot = onAddPilot
            )
        }
        composable("editPilot/{pilotId}") { backStackEntry ->
            val pilotId = backStackEntry.arguments?.getString("pilotId")
            val pilot = pilots.find { it.id.toString() == pilotId }
            pilot?.let {
                EditPilotScreen(
                    pilot = it,
                    onSave = { updatedPilot ->
                        pilotViewModel.updatePilot(updatedPilot)
                        navController.popBackStack()
                    },
                    onDelete = { pilotToDelete ->
                        pilotViewModel.deletePilot(pilotToDelete)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
