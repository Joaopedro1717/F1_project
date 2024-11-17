package com.example.f1_project.data.models

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.f1_project.data.db.PilotDao
import com.example.f1_project.data.db.PilotDatabase
import kotlinx.coroutines.flow.Flow

class PilotRepository(application: Application) {

    private val pilotDao: PilotDao = PilotDatabase.getDatabase(application).pilotDao()

    suspend fun upsertPilot(pilot: Pilot) {
        pilotDao.insertPilot(pilot)
    }

    fun getAllPilots(): Flow<List<Pilot>> {
        return pilotDao.getAllPilots()
    }

    suspend fun upsertAll(pilots: List<Pilot>) {
        val existingPilots = pilotDao.getAllPilotsOnce()
        val existingNames = existingPilots.map { it.name }.toSet() // Use um identificador único como 'name'

        val newPilots = pilots.filter { it.name !in existingNames } // Filtra apenas os novos pilotos
        val updatedPilots = pilots.filter { it.name in existingNames } // Identifica os que precisam ser atualizados

        pilotDao.insertAll(newPilots) // Insere os novos
        updatedPilots.forEach { pilotDao.updatePilot(it) } // Atualiza os existentes
    }


    suspend fun getPilotById(id: Long): Pilot? {
        return pilotDao.getPilotById(id)
    }

    suspend fun updatePilot(pilot: Pilot) {
        pilotDao.updatePilot(pilot)
    }

    suspend fun deletePilot(pilot: Pilot) {
        pilotDao.deletePilot(pilot)
    }
}

