package com.example.f1_project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.f1_project.functions.getPilots

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            // Use Surface para aplicar o shape arredondado
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp), // Ajuste a altura conforme necessário
                color = Color.LightGray, // Personalize a cor da bottom bar
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp) // Adicione bordas arredondadas
            ) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.Transparent, // Deixe transparente para mostrar a cor do Surface
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center // Centraliza o ícone
                    ) {
                        IconButton(onClick = { navController.navigate("profile") }) {
                            Icon(Icons.Filled.Person, contentDescription = "Profile")
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        PilotsScreen(
            pilots = getPilots(),
            onDetailsClick = { pilot -> navController.navigate("details/${pilot.name}") },
            modifier = Modifier.padding(paddingValues) // Ajusta o espaçamento da tela com relação à bottom bar
        )
    }
}
