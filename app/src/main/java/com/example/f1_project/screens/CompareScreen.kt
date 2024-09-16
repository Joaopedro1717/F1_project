package com.example.f1_project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.f1_project.data_class.Pilot
import androidx.compose.ui.Modifier

@Composable
fun CompareScreen(pilot1: Pilot, pilot2: Pilot) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = pilot1.imageRes),
                contentDescription = "${pilot1.name}'s photo",
                modifier = Modifier.size(150.dp)
            )
            Text(text = pilot1.name, style = MaterialTheme.typography.headlineSmall)
            PilotStatistics(pilot = pilot1)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = pilot2.imageRes),
                contentDescription = "${pilot2.name}'s photo",
                modifier = Modifier.size(150.dp)
            )
            Text(text = pilot2.name, style = MaterialTheme.typography.headlineSmall)
            PilotStatistics(pilot = pilot2)
        }
    }
}

@Composable
fun PilotStatistics(pilot: Pilot) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Vitórias em Grandes Prêmios: ${pilot.grandPrixWins}")
        Text(text = "Títulos Mundiais: ${pilot.worldTitles}")
        Text(text = "Pole Positions: ${pilot.polePositions}")
        Text(text = "Equipes anteriores: ${pilot.previousTeams.joinToString()}")
    }
}
