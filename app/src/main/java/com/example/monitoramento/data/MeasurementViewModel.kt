package com.example.monitoramento.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MeasurementViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).measurementDao()
    private val repo = MeasurementRepository(dao)

    val data = repo.allData

    fun save(batimentos: Int, peso: Float, glicemia: Int) {
        viewModelScope.launch {
            repo.save(
                Measurement(
                    batimentos = batimentos,
                    peso = peso,
                    glicemia = glicemia
                )
            )
        }
    }

    fun delete(m: Measurement) {
        viewModelScope.launch {
            repo.delete(m)
        }
    }

}
