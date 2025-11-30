package com.example.monitoramento.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// IMPORTANTE:
import com.example.monitoramento.data.Measurement
import com.example.monitoramento.data.MeasurementDao

@Database(entities = [Measurement::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun measurementDao(): MeasurementDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "monitoramento_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
