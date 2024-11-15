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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.f1_project.data.models.Pilot

@Composable
fun PilotsScreen(
    pilots: List<Pilot>,
    onDetailsClick: (Pilot) -> Unit,
    onEditClick: (Pilot) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize()
    ) {
        items(pilots) { pilot ->
            PilotCard(
                pilot = pilot,
                onDetailsClick = { onDetailsClick(pilot) },
                onEditClick = { onEditClick(pilot) }
            )
        }
    }
}

@Composable
fun PilotCard(
    pilot: Pilot,
    onDetailsClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onEditClick() }
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
            Button(
                onClick = onDetailsClick,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "Ver detalhes")
            }
        }
    }
}

