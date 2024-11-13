package com.example.f1_project.data.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PilotViewModel(application: Application) : AndroidViewModel(application) {
    private val pilotRepository = PilotRepository(application)
    private val _pilots = MutableLiveData<List<Pilot>>()
    val pilots: LiveData<List<Pilot>> = _pilots

    init {
        loadPilots()
    }

    private fun loadPilots() {
        // Carregar a lista de pilotos a partir do banco de dados local (Room)
        viewModelScope.launch {
            try {
                _pilots.value = pilotRepository.getAllPilots()
            } catch (e: Exception) {
                // Tratar erro aqui, como por exemplo mostrar uma mensagem de erro
            }
        }
    }

    fun addPilot(pilot: Pilot) {
        // Inserir o piloto no banco de dados local (Room)
        viewModelScope.launch {
            try {
                pilotRepository.insertPilot(pilot)  // Inserir no Room
                _pilots.value = pilotRepository.getAllPilots()  // Atualizar lista
            } catch (e: Exception) {
                // Tratar erro aqui, como por exemplo mostrar uma mensagem de erro
            }
        }
    }
}

