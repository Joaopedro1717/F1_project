package com.example.f1_project.data.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PilotViewModel(application: Application) : AndroidViewModel(application) {
    private val pilotRepository = PilotRepository(application)

    val pilots: LiveData<List<Pilot>> = pilotRepository.getAllPilots().asLiveData()

    fun addPilot(pilot: Pilot) {
        viewModelScope.launch {
            try {
                pilotRepository.insertPilot(pilot)
            } catch (e: Exception) {
                // Tratar erro aqui
            }
        }
    }

    fun updatePilot(pilot: Pilot) {
        viewModelScope.launch {
            pilotRepository.updatePilot(pilot)
        }
    }

    // Corrigido: deletePilot agora está dentro do escopo da classe
    fun deletePilot(pilot: Pilot) {
        viewModelScope.launch {
            pilotRepository.deletePilot(pilot)
        }
    }
}


