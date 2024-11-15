package com.example.f1_project.data.models

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.f1_project.data.db.PilotDao
import com.example.f1_project.data.db.PilotDatabase
import kotlinx.coroutines.flow.Flow

class PilotRepository(application: Application) {

    private val pilotDao: PilotDao = PilotDatabase.getDatabase(application).pilotDao()

    // Função para inserir um piloto
    suspend fun insertPilot(pilot: Pilot) {
        pilotDao.insertPilot(pilot)
    }

    // Função para obter todos os pilotos
    fun getAllPilots(): Flow<List<Pilot>> {
        return pilotDao.getAllPilots()
    }

    // Função para obter um piloto pelo ID
    suspend fun getPilotById(id: Long): Pilot? {
        return pilotDao.getPilotById(id)
    }

    // Função para atualizar um piloto
    suspend fun updatePilot(pilot: Pilot) {
        pilotDao.updatePilot(pilot)
    }

    // Função para excluir um piloto
    suspend fun deletePilot(pilot: Pilot) {
        pilotDao.deletePilot(pilot)
    }

    // Função para "upsert" (inserir ou atualizar) um piloto
    suspend fun upsertPilot(pilot: Pilot) {
        val existingPilot = pilotDao.getPilotById(pilot.id)
        if (existingPilot != null) {
            pilotDao.updatePilot(pilot)
        } else {
            pilotDao.insertPilot(pilot)
        }
    }
}
