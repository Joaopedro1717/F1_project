package com.example.f1_project.data.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PilotDao {
    @Insert
    suspend fun insertPilot(pilot: Pilot)

    @Query("SELECT * FROM pilots")
    suspend fun getAllPilots(): List<Pilot>

    @Query("SELECT * FROM pilots")
    fun listar(): Flow<List<Pilot>>

    @Query("SELECT * FROM pilots WHERE id = :idx")
    suspend fun getById(idx: Int): Pilot

    @Update
    suspend fun updatePilot(pilot: Pilot)

    @Upsert
    suspend fun upsertPilot(pilot: Pilot)

    @Delete
    suspend fun deletePilot(pilot: Pilot)
}
