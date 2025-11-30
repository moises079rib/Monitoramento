package com.example.monitoramento

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.monitoramento.data.MeasurementViewModel

@Composable
fun DataEntryScreen(navController: NavController) {

    val viewModel: MeasurementViewModel = viewModel()

    var batimentos by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var glicemia by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Inserir Dados",
            style = MaterialTheme.typography.headlineMedium
        )

        // Campo Batimentos
        OutlinedTextField(
            value = batimentos,
            onValueChange = { batimentos = it },
            label = { Text("Batimentos (BPM)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Peso
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Glicemia
        OutlinedTextField(
            value = glicemia,
            onValueChange = { glicemia = it },
            label = { Text("Glicemia (mg/dL)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Botão SALVAR
        Button(
            onClick = {
                val b = batimentos.toIntOrNull()
                val p = peso.toFloatOrNull()
                val g = glicemia.toIntOrNull()

                if (b != null && p != null && g != null) {
                    viewModel.save(b, p, g)

                    // Limpa os campos
                    batimentos = ""
                    peso = ""
                    glicemia = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Dados")
        }

        // Botão HISTÓRICO
        Button(
            onClick = {
                navController.navigate("history")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Histórico")
        }
    }
}
