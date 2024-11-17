package com.example.f1_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.f1_project.data.models.Pilot
import com.example.f1_project.data.models.PilotViewModel
import com.example.f1_project.navigation.AppNavGraph
import com.example.f1_project.ui.theme.F1ProjectTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            F1ProjectTheme {
                val navController = rememberNavController()

                // Controle de tema com `rememberSaveable`
                val isDarkTheme = rememberSaveable { mutableStateOf(false) }

                // Criação do viewModel dentro do contexto composable
                val pilotViewModel: PilotViewModel = viewModel()

                //Sincroniza dados do Firestore com o ViewModel
                pilotViewModel.syncWithFirestore()

                // Inicia o listener para Firestore
                pilotViewModel.startFirestoreListener()

                // Passa a função onAddPilot que usa o viewModel
                val onAddPilot: (Pilot) -> Unit = { pilot ->
                    // Lógica para adicionar o piloto, agora usando o viewModel diretamente
                    pilotViewModel.addPilot(pilot)
                }

                // Passa os valores para o `AppNavGraph`
                AppNavGraph(
                    navController = navController,
                    isDarkTheme = isDarkTheme.value,
                    onThemeChange = { isDarkTheme.value = it },
                    onAddPilot = onAddPilot // Passa a função normal
                )
            }
        }
    }
}
