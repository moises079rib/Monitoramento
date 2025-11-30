package com.example.monitoramento.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measurements")
data class Measurement(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val batimentos: Int,
    val peso: Float,
    val glicemia: Int,
    val timestamp: Long = System.currentTimeMillis()
)