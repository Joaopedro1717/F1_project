package com.example.f1_project.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.f1_project.data.models.Pilot
import com.example.f1_project.data.models.PilotDao

@Database(entities = [Pilot::class], version = 1, exportSchema = false)
abstract class PilotDB : RoomDatabase() {
    abstract fun pilotDao(): PilotDao

    companion object {
        @Volatile
        private var INSTANCE: PilotDB? = null

        fun getDatabase(context: Context): PilotDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PilotDB::class.java,
                    "f1_database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
