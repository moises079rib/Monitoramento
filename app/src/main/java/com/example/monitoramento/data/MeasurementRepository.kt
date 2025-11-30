package com.example.monitoramento.data

class MeasurementRepository(private val dao: MeasurementDao) {

    val allData = dao.getAll()

    suspend fun save(m: Measurement) {
        dao.insert(m)
    }

    suspend fun delete(m: Measurement) {
        dao.delete(m)
    }
}