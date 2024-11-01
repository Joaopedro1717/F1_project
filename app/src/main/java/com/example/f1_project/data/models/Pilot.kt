package com.example.f1_project.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "pilots")
@TypeConverters(Converters::class)
data class Pilot (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val team: String,
    val nacionality: String,
    val age: Int,
    val previousTeams: List<String>,
    val grandPrixWins: Int,
    val worldTitles: Int,
    val polePositions: Int,
    val imageRes: Int
)
