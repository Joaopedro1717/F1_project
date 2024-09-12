package com.example.f1_project.data_class

data class Pilot (
    val name: String,
    val team: String,
    val imageRes: Int,
    val nacionality: String,
    val age: Int,
    val previousTeams: List<String>,
    val grandPrixWins: Int,
    val worldTitles: Int,
    val polePositions: Int
)
