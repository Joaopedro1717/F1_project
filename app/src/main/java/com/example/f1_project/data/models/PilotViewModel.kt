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

                firestore.collection("teste")
                    .add(pilot)
                    .addOnSuccessListener {
                        println("Pilot added to Firestore with ID: ${it.id}")
                    }
                    .addOnFailureListener { e ->
                        e.printStackTrace()
                    }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updatePilot(pilot: Pilot) {
        viewModelScope.launch {
            try {
                // Atualiza o banco de dados local
                pilotRepository.updatePilot(pilot)

                // Atualiza o Firestore
                val query = firestore.collection("teste")
                    .whereEqualTo("name", pilot.name) // Filtra pelo nome ou outro campo único
                    .get()

                query.addOnSuccessListener { snapshot ->
                    if (!snapshot.isEmpty) {
                        val docId = snapshot.documents.first().id
                        firestore.collection("teste")
                            .document(docId)
                            .set(pilot) // Substitui o documento no Firestore
                            .addOnSuccessListener {
                                println("Pilot successfully updated in Firestore")
                            }
                            .addOnFailureListener { e ->
                                e.printStackTrace()
                            }
                    } else {
                        println("No matching pilot found in Firestore for update")
                    }
                }.addOnFailureListener { e ->
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun deletePilot(pilot: Pilot) {
        viewModelScope.launch {
            // Exclui do banco de dados local
            pilotRepository.deletePilot(pilot)

            // Exclui do Firestore
            val query = firestore.collection("teste")
                .whereEqualTo("name", pilot.name) // Pode ser outro campo único, como 'id'
                .get()

            query.addOnSuccessListener { snapshot ->
                if (!snapshot.isEmpty) {
                    val docId = snapshot.documents.first().id
                    firestore.collection("teste")
                        .document(docId)
                        .delete() // Deleta o documento do Firestore
                        .addOnSuccessListener {
                            println("Pilot successfully deleted from Firestore")
                        }
                        .addOnFailureListener { e ->
                            e.printStackTrace()
                        }
                } else {
                    println("No matching pilot found in Firestore for deletion")
                }
            }.addOnFailureListener { e ->
                e.printStackTrace()
            }
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


