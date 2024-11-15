package com.example.f1_project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.f1_project.data.models.Pilot

@Composable
fun MainScreen(
    navController: NavController,
    pilots: List<Pilot>, // Agora recebendo a lista de pilotos
    onAddPilot: (Pilot) -> Unit // Recebendo a função de adicionar piloto
) {
    val selectedScreen = remember { mutableStateOf<String?>(null) }

    Scaffold(
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                color = Color.LightGray,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.Transparent,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate("profile")
                                selectedScreen.value = "profile"
                            }) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Profile",
                                tint = if (selectedScreen.value == "profile") MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.width(24.dp))

                        IconButton(
                            onClick = {
                                navController.navigate("settings")
                                selectedScreen.value = "settings"
                            }) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings",
                                tint = if (selectedScreen.value == "settings") MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addPilot") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pilot")
            }
        }
    ) { paddingValues ->
        // Exibindo a lista de pilotos
        PilotsScreen(
            pilots = pilots,
            onDetailsClick = { pilot -> navController.navigate("details/${pilot.name}") },
            modifier = Modifier.padding(paddingValues)
        )
    }
}
