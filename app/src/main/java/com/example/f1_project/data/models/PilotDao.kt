package com.example.f1_project.data.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PilotDao {

    @Insert
    suspend fun insertPilot(pilot: Pilot)

    @Query("SELECT * FROM pilots")  // Corrigido o nome da tabela de "pilot" para "pilots"
    suspend fun getAllPilots(): List<Pilot>

    @Query("SELECT * FROM pilots WHERE id = :id")  // Corrigido o nome da tabela de "pilot" para "pilots"
    suspend fun getPilotById(id: Long): Pilot?  // Corrigido para Long, se você alterar o ID para Long

    @Update
    suspend fun updatePilot(pilot: Pilot)

    @Delete
    suspend fun deletePilot(pilot: Pilot)
}

