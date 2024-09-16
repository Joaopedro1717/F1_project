package com.example.f1_project.functions

import com.example.f1_project.R
import com.example.f1_project.data_class.Pilot

fun getPilots(): List<Pilot> {
    return listOf(
        Pilot(name = "Lewis Hamilton", team = "Mercedes", nacionality = "Britânico",
              age = 37, previousTeams = listOf("McLaren"), grandPrixWins = 104,
              worldTitles = 7, polePositions = 103, imageRes = android.R.drawable.ic_menu_gallery),
    )
}