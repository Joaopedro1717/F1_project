package com.example.f1_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
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
        ) {
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
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val updatedPilot = pilot.copy(name = name, team = team, nationality = nationality)
                    onSave(updatedPilot) // Salva as alterações do piloto
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Salvar")
            }
        }
    }
}

