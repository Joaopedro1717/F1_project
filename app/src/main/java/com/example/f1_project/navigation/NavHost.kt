package com.example.f1_project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.f1_project.functions.getPilots
import com.example.f1_project.screens.CompareScreen
import com.example.f1_project.screens.PilotsScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "pilots") {
        composable("pilots") {
            PilotsScreen(pilots = getPilots(), onCompareClick = { pilot1, pilot2 ->
                navController.navigate("compare/${pilot1.name}/${pilot2.name}")
            })
        }
        composable("compare/{pilot1}/{pilot2}") { backStackEntry ->
            val pilot1Name = backStackEntry.arguments?.getString("pilot1Name")
            val pilot2Name = backStackEntry.arguments?.getString("pilot2Name")

            val pilot1 = getPilots().find { it.name == pilot1Name }
            val pilot2 = getPilots().find { it.name == pilot2Name }

            if (pilot1 != null && pilot2 != null) {
                CompareScreen(pilot1 = pilot1, pilot2 = pilot2)
            }
        }
    }
}