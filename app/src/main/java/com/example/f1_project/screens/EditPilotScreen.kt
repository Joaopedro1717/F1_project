package com.example.f1_project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.f1_project.data.models.Pilot

@Composable
fun EditPilotScreen(
    pilot: Pilot,
    onSave: (Pilot) -> Unit,
    onDelete: (Pilot) -> Unit, // Função para deletar o piloto
) {
    // Estados para armazenar os valores dos campos
    var name by remember { mutableStateOf(pilot.name) }
    var team by remember { mutableStateOf(pilot.team) }
    var nationality by remember { mutableStateOf(pilot.nationality) }
    var age by remember { mutableStateOf(pilot.age.toString()) }
    var grandPrixWins by remember { mutableStateOf(pilot.grandPrixWins.toString()) }
    var worldTitles by remember { mutableStateOf(pilot.worldTitles.toString()) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onDelete(pilot) }, // Aciona a função de deletar
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Excluir Piloto")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Permite rolagem em telas menores
        ) {
            // Campos de edição
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = team,
                onValueChange = { team = it },
                label = { Text("Equipe") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = nationality,
                onValueChange = { nationality = it },
                label = { Text("País") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = age,
                onValueChange = { if (it.toIntOrNull() != null || it.isEmpty()) age = it },
                label = { Text("Idade") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = grandPrixWins,
                onValueChange = { if (it.toIntOrNull() != null || it.isEmpty()) grandPrixWins = it },
                label = { Text("Vitórias em GP") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = worldTitles,
                onValueChange = { if (it.toIntOrNull() != null || it.isEmpty()) worldTitles = it },
                label = { Text("Títulos Mundiais") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botão de salvar
            Button(
                onClick = {
                    val updatedPilot = pilot.copy(
                        name = name,
                        team = team,
                        nationality = nationality,
                        age = age.toIntOrNull() ?: pilot.age,
                        grandPrixWins = grandPrixWins.toIntOrNull() ?: pilot.grandPrixWins,
                        worldTitles = worldTitles.toIntOrNull() ?: pilot.worldTitles
                    )
                    onSave(updatedPilot) // Salva as alterações do piloto
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Salvar")
            }
        }
    }
}
