package com.example.f1_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.f1_project.data.models.Pilot

@Composable
fun EditPilotScreen(
    pilot: Pilot,
    onSave: (Pilot) -> Unit,
) {
    // Estados para armazenar os valores dos campos
    var name by remember { mutableStateOf(pilot.name) }
    var team by remember { mutableStateOf(pilot.team) }
    var nationality by remember { mutableStateOf(pilot.nationality) }

    // Botão de salvar
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = team,
            onValueChange = { team = it },
            label = { Text("Equipe") },
            modifier = Modifier.fillMaxWidth()
        )
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
                onSave(updatedPilot)
            },
            modifier = Modifier.align(Alignment.End) // Corrigido aqui
        ) {
            Text("Salvar")
        }
    }
}
