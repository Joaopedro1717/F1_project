package com.example.f1_project.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.f1_project.data.models.Converters // Corrija a importação aqui
import com.example.f1_project.data.models.Pilot

@Database(entities = [Pilot::class], version = 1)
@TypeConverters(Converters::class) // Certifique-se de que a classe Converters seja corretamente referenciada
abstract class PilotDatabase : RoomDatabase() {
    abstract fun pilotDao(): PilotDao

    companion object {
        @Volatile
        private var INSTANCE: PilotDatabase? = null

        fun getDatabase(context: Context): PilotDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PilotDatabase::class.java,
                    "pilot_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
