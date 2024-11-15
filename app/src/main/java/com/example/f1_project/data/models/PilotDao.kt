package com.example.f1_project.data.db

import androidx.room.*
import com.example.f1_project.data.models.Pilot
import kotlinx.coroutines.flow.Flow

@Dao
interface PilotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPilot(pilot: Pilot)

    @Query("SELECT * FROM pilots")
    fun getAllPilots(): Flow<List<Pilot>> // Retorna Flow para observar as mudanças

    @Query("SELECT * FROM pilots WHERE id = :id")
    suspend fun getPilotById(id: Long): Pilot?

    @Update
    suspend fun updatePilot(pilot: Pilot)

    @Delete
    suspend fun deletePilot(pilot: Pilot)
}

