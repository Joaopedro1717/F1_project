package com.example.f1_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import com.example.f1_project.data.models.Pilot
import com.example.f1_project.navigation.AppNavGraph
import com.example.f1_project.ui.theme.F1ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            F1ProjectTheme {
                // Inicializa a lista de pilotos com `mutableStateListOf`
                val pilots = remember { mutableStateListOf<Pilot>() }

                // Função para adicionar um novo piloto
                val onAddPilot: (Pilot) -> Unit = { newPilot ->
                    pilots.add(newPilot)
                }

                val navController = rememberNavController()

                // Controle de tema com `rememberSaveable`
                val isDarkTheme = rememberSaveable { mutableStateOf(false) }

                // Passa os valores corretamente para o `AppNavGraph`
                AppNavGraph(
                    navController = navController,
                    isDarkTheme = isDarkTheme.value,
                    onThemeChange = { isDarkTheme.value = it },
                    onAddPilot = onAddPilot,
                    pilots = pilots
                )
            }
        }
    }
}
