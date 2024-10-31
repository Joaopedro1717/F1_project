package com.example.f1_project.functions

import com.example.f1_project.R
import com.example.f1_project.data.models.Pilot

fun getPilots(): List<Pilot> {
    return listOf(
        Pilot(name = "Lewis Hamilton", team = "Mercedes", nacionality = "Britânico",
              age = 37, previousTeams = listOf("McLaren"), grandPrixWins = 104,
              worldTitles = 7, polePositions = 103, imageRes = R.drawable.lewis_hamilton),
        Pilot(name = "Max Verstappen",  team = "Red Bull", nacionality = "Holandês",
              age = 25, previousTeams = listOf("Toro Rosso"), grandPrixWins = 60,
              worldTitles = 2, polePositions = 24, imageRes = R.drawable.max_verstappen),
        Pilot(name = "Charles Leclerc", team = "Ferrari", nacionality = "Monegasco",
              age = 24, previousTeams = listOf("Sauber"), grandPrixWins = 7,
            worldTitles = 0, polePositions = 26, imageRes = R.drawable.charles_leclerc),
        Pilot(name = "Lando Norris", team = "McLaren", nacionality = "Britânico",
            age = 23, previousTeams = listOf("Carlin Motosport"), grandPrixWins = 2,
            worldTitles = 0, polePositions = 5, imageRes = R.drawable.lando_norris),
        Pilot(name = "F. Alonso", team = "Aston Martin", nacionality = "Espanhol",
            age = 42, previousTeams = listOf("Alpine","Mclaren","Ferrari","Renault","Minardi"), grandPrixWins = 117,
            worldTitles = 2, polePositions = 12, imageRes = R.drawable.fernando_alonso),
        Pilot(name = "Pierre Gasly", team = "Alpine", nacionality = "Francês",
            age = 27, previousTeams = listOf("AlphaTauri"), grandPrixWins = 1,
            worldTitles = 0, polePositions = 0, imageRes = R.drawable.pierre_gasly),
        Pilot(name = "N. Hulkenberg", team = "Haas", nacionality = "Alemão",
            age = 36, previousTeams = listOf("Renault"), grandPrixWins = 0,
            worldTitles = 0, polePositions = 0, imageRes = R.drawable.nico_hulkenberg),
        Pilot(name = "Valtteri Bottas", team = "Sauber", nacionality = "Finlandês",
            age = 35, previousTeams = listOf("Mercedes", "Williams"), grandPrixWins = 11,
            worldTitles = 0, polePositions = 10, imageRes = R.drawable.valtteri_bottas),
        Pilot(name = "Daniel Ricciardo", team = "Visa RB", nacionality = "Australiano",
            age = 34, previousTeams = listOf("McLaren", "Renaut", "Red Bull", "Toro Rosso"), grandPrixWins = 11,
            worldTitles = 0, polePositions = 10, imageRes = R.drawable.daniel_ricciardo),
        Pilot(name = "Alexander Albon", team = "Williams", nacionality = "Tailandês",
            age = 27, previousTeams = listOf("Toro Rosso"), grandPrixWins = 0,
            worldTitles = 0, polePositions = 0, imageRes = R.drawable.alexander_albon)
    )
}