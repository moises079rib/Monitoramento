package com.example.monitoramento

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(nav: NavController) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(20.dp))

        TextField(value = email, onValueChange = { email = it }, label = { Text("E-mail") })
        Spacer(Modifier.height(12.dp))
        TextField(value = senha, onValueChange = { senha = it }, label = { Text("Senha") })

        Spacer(Modifier.height(20.dp))

        Button(onClick = { nav.navigate("data") }, modifier = Modifier.fillMaxWidth()) {
            Text("Entrar")
        }

        Spacer(Modifier.height(12.dp))

        TextButton(onClick = { nav.navigate("register") }) {
            Text("Criar conta")
        }
    }
}
