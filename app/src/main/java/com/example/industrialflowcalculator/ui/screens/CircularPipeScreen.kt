package com.example.industrialflowcalculator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.industrialflowcalculator.ui.components.InputField
import com.example.industrialflowcalculator.ui.components.ResultCard
import com.example.industrialflowcalculator.viewmodel.CircularPipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CircularPipeScreen(navController: NavController, vm: CircularPipeViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tubulação Circular") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Fórmula: A = π(D/2)², Q = A × v", fontWeight = FontWeight.Bold)
            InputField(value = state.diameter, onValueChange = vm::onDiameterChange, label = "Diâmetro interno", unit = "mm")
            InputField(value = state.velocity, onValueChange = vm::onVelocityChange, label = "Velocidade do fluido", unit = "m/s")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = vm::calculate, modifier = Modifier.weight(1f)) { Text("Calcular") }
                OutlinedButton(onClick = vm::clear, modifier = Modifier.weight(1f)) { Text("Limpar") }
            }
            if (state.error.isNotEmpty()) {
                Text(state.error, color = MaterialTheme.colorScheme.error)
            }
            if (state.result.isNotEmpty()) {
                ResultCard(
                    title = "Resultado",
                    lines = state.result.lines().map { it.split(":").let { p -> if (p.size >= 2) p[0] to p.drop(1).joinToString(":").trim() else "" to it } }
                )
            }
        }
    }
}
