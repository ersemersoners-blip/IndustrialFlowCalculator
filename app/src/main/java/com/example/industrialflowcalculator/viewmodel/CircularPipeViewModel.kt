package com.example.industrialflowcalculator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.PI

data class CircularPipeState(
    val diameter: String = "",
    val velocity: String = "",
    val result: String = "",
    val error: String = ""
)

class CircularPipeViewModel : ViewModel() {
    private val _state = MutableStateFlow(CircularPipeState())
    val state: StateFlow<CircularPipeState> = _state.asStateFlow()

    fun onDiameterChange(v: String) { _state.value = _state.value.copy(diameter = v) }
    fun onVelocityChange(v: String) { _state.value = _state.value.copy(velocity = v) }

    fun calculate() {
        val d = _state.value.diameter.replace(",", ".").toDoubleOrNull()
        val v = _state.value.velocity.replace(",", ".").toDoubleOrNull()
        if (d == null || v == null || d <= 0) {
            _state.value = _state.value.copy(error = "Preencha todos os campos corretamente.", result = "")
            return
        }
        val r = (d / 1000.0) / 2.0
        val a = PI * r * r
        val q = a * v
        val result = "Área: %.6f m²\nQ = %.4f m³/s\n= %.2f m³/h\n= %.2f L/min\n= %.2f L/s".format(
            a, q, q * 3600, q * 60000, q * 1000
        )
        _state.value = _state.value.copy(result = result, error = "")
    }

    fun clear() { _state.value = CircularPipeState() }
}
