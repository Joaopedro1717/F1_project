package com.example.f1_project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.f1_project.data_class.Pilot
import androidx.compose.ui.Modifier


@Composable
fun PilotsScreen(pilots: List<Pilot>, onCompareClick: (Pilot, Pilot) -> Unit) {
    val selectedPilots = remember { mutableStateListOf<Pilot>() }
    val showDialog = remember { mutableStateOf(false) }
    val pilotForDetails = remember { mutableStateOf<Pilot?>(null) }

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(pilots) { pilot ->
                    PilotCard(
                        pilot = pilot,
                        onDetailsClick = {
                            pilotForDetails.value = pilot
                            showDialog.value = true
                        },
                        onSelectPilot = {
                            if (selectedPilots.size < 2 && !selectedPilots.contains(pilot)) {
                                selectedPilots.add(pilot)
                            }
                        }
                    )
            }
        }

        if(selectedPilots.size == 2) {
            Button(onClick = { onCompareClick(selectedPilots[0], selectedPilots[1] )} ) {
                Text(text = "Comparação")
            }
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = pilotForDetails.value?.name ?: "") },
            text = {
                pilotForDetails.value?.let {
                    Text(text = "Nacionalidade: ${it.nacionality}\n" +
                            "Idade: ${it.age}\n" +
                            "Equipes anteriores: ${it.previousTeams.joinToString()}")
                }
            },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("Fechar")
                }
            }
        )
    }
}

@Composable
fun PilotCard(pilot: Pilot, onDetailsClick: () -> Unit, onSelectPilot: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onSelectPilot() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = pilot.imageRes),
                contentDescription = "${pilot.name}'s photo",
                modifier = Modifier.size(100.dp)
            )
            Text(text = pilot.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = pilot.team, style = MaterialTheme.typography.bodyMedium)
            Button(onClick = onDetailsClick) {
                Text(text = "Ver detalhes")
            }
        }
    }
}