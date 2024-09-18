package com.example.f1_project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.f1_project.data_class.Pilot
import androidx.compose.foundation.layout.*


@Composable
fun PilotDetailsScreen(pilot: Pilot) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = pilot.imageRes),
            contentDescription = "${pilot.name}'s photo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = pilot.name, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Equipe: ${pilot.team}")
        Text(text = "Nacionalidade: ${pilot.nacionality}")
        Text(text = "Idade: ${pilot.age}")
        Text(text = "Vitórias em Grandes Prêmios: ${pilot.grandPrixWins}")
        Text(text = "Títulos Mundiais: ${pilot.worldTitles}")
        Text(text = "Pole Positions: ${pilot.polePositions}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Equipes anteriores: ${pilot.previousTeams.joinToString()}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Biografia: Aqui você pode adicionar a biografia do piloto.")
    }
}