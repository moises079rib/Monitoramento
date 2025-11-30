package com.example.monitoramento

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.monitoramento.data.Measurement
import com.example.monitoramento.data.MeasurementViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(navController: NavController) {

    val viewModel: MeasurementViewModel = viewModel()
    val dados = viewModel.data.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Histórico de Registros",
                style = MaterialTheme.typography.headlineMedium
            )

            Button(onClick = { navController.navigateUp() }) {
                Text("Voltar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(dados.value) { item ->

                val dataFormatada = SimpleDateFormat(
                    "dd/MM/yyyy HH:mm",
                    Locale.getDefault()
                ).format(Date(item.timestamp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("Batimentos: ${item.batimentos} BPM")
                        Text("Peso: ${item.peso} Kg")
                        Text("Glicemia: ${item.glicemia} mg/dL")
                        Text("Data: $dataFormatada")

                        Spacer(modifier = Modifier.height(8.dp))

                        // BOTÃO EXCLUIR REGISTRO
                        Button(
                            onClick = { viewModel.delete(item) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Excluir Registro")
                        }
                    }
                }
            }
        }
    }
}
