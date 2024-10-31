package com.example.f1_project.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.f1_project.data.models.PilotDao

abstract class PilotDB : RoomDatabase() {
    abstract fun pilotDao(): PilotDao
}

fun getDatabase(context: Context): PilotDB {
    return Room.databaseBuilder(
        context.applicationContext,
        PilotDB::class.java,
        name = "f1_database.db"
    ).build()
}