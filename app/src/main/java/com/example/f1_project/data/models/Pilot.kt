package com.example.f1_project.data.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "pilots")
data class Pilot(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",
    val team: String = "",
    val nationality: String = "",
    val age: Int = 0,
    val previousTeams: List<String> = emptyList(),
    val grandPrixWins: Int = 0,
    val worldTitles: Int = 0,
    val polePositions: Int = 0,
    @DrawableRes val imageRes: Int = 0
) {
    constructor() : this(0, "", "", "", 0, emptyList(), 0, 0, 0, 0)
}

