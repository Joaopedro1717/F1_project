package com.example.f1_project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.f1_project.R
import com.example.f1_project.data.models.Pilot

@Composable
fun AddPilotScreen(navController: NavController, onAddPilot: (Pilot) -> Unit) {
    var name by remember { mutableStateOf("") }
    var team by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var grandPrixWins by remember { mutableStateOf("") }
    var polePositions by remember { mutableStateOf("") }
    var worldTitles by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Adicionar Novo Piloto", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = team,
            onValueChange = { team = it },
            label = { Text("Equipe") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nationality,
            onValueChange = { nationality = it },
            label = { Text("Nacionalidade") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Idade") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = grandPrixWins,
            onValueChange = { grandPrixWins = it },
            label = { Text("Vitórias em GP") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = worldTitles,
            onValueChange = { worldTitles = it },
            label = { Text("Títulos Mundiais") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newPilot = Pilot(
                    name = name,
                    team = team,
                    nationality = nationality,
                    age = age.toIntOrNull() ?: 0,
                    previousTeams = emptyList(),
                    grandPrixWins = grandPrixWins.toIntOrNull() ?: 0,
                    worldTitles = worldTitles.toIntOrNull() ?: 0,
                    polePositions = polePositions.toIntOrNull() ?: 0,
                    imageRes = R.drawable.lewis_hamilton// Defina uma imagem padrão ou deixe vazia para customizar depois
                )
                onAddPilot(newPilot)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Piloto")
        }
    }
}
