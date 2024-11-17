package com.example.f1_project.data.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PilotViewModel(application: Application) : AndroidViewModel(application) {

    private val pilotRepository = PilotRepository(application)
    private val firestore = FirebaseFirestore.getInstance()

    private var isFirestoreListenerActive = false // Controle para evitar múltiplos listeners

    val pilots: LiveData<List<Pilot>> = pilotRepository.getAllPilots().asLiveData()

    fun syncWithFirestore() {
        firestore.collection("teste")
            .get()
            .addOnSuccessListener { snapshot ->
                val pilotsFromFirestore = snapshot.documents.mapNotNull { it.toObject(Pilot::class.java) }
                viewModelScope.launch(Dispatchers.IO) {
                    // Atualiza ou insere sem duplicação
                    pilotRepository.upsertAll(pilotsFromFirestore)
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace() // Log de erro
            }
    }


    fun addPilot(pilot: Pilot) {
        viewModelScope.launch {
            try {
                pilotRepository.upsertPilot(pilot)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updatePilot(pilot: Pilot) {
        viewModelScope.launch {
            pilotRepository.updatePilot(pilot)
        }
    }

    fun deletePilot(pilot: Pilot) {
        viewModelScope.launch {
            pilotRepository.deletePilot(pilot)
        }
    }

    fun startFirestoreListener() {
        if (isFirestoreListenerActive) return // Evita múltiplas inscrições
        isFirestoreListenerActive = true

        firestore.collection("teste")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    error.printStackTrace()
                    return@addSnapshotListener
                }

                snapshot?.let {
                    val pilotsFromFirestore = it.documents.mapNotNull { doc -> doc.toObject(Pilot::class.java) }
                    viewModelScope.launch(Dispatchers.IO) {
                        // Atualiza ou insere apenas mudanças reais
                        pilotRepository.upsertAll(pilotsFromFirestore)
                    }
                }
            }
    }

}


