package com.example.monitoramento

import android.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.example.monitoramento.data.MeasurementViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GraphScreen() {

    val viewModel: MeasurementViewModel = viewModel()
    val dados = viewModel.data.collectAsState(initial = emptyList())

    AndroidView(factory = { context ->
        LineChart(context).apply {

            val batList = ArrayList<Entry>()
            val pesoList = ArrayList<Entry>()
            val glicList = ArrayList<Entry>()

            dados.value.forEachIndexed { index, item ->
                batList.add(Entry(index.toFloat(), item.batimentos.toFloat()))
                pesoList.add(Entry(index.toFloat(), item.peso))
                glicList.add(Entry(index.toFloat(), item.glicemia.toFloat()))
            }

            val batData = LineDataSet(batList, "Batimentos").apply { color = Color.RED }
            val pesoData = LineDataSet(pesoList, "Peso").apply { color = Color.BLUE }
            val glicData = LineDataSet(glicList, "Glicemia").apply { color = Color.GREEN }

            val lineData = LineData(batData, pesoData, glicData)
            data = lineData

            description.isEnabled = false
            animateX(1000)
        }
    })
}