package com.example.f1_project.data.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pilots")
data class Pilot(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,  // Alterado para Long
    val name: String,
    val team: String,
    val nationality: String,  // Corrigido de "nacionality" para "nationality"
    val age: Int,
    val previousTeams: List<String>, // Se você estiver usando Room, precisará de um conversor para List<String>
    val grandPrixWins: Int,
    val worldTitles: Int,
    val polePositions: Int,
    @DrawableRes val imageRes: Int
)

