package com.example.industrialflowcalculator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

data class VolumetricFlowState(
    val area: String = "",
    val velocity: String = "",
    val result: String = "",
    val error: String = ""
)

class VolumetricFlowViewModel : ViewModel() {
    private val _state = MutableStateFlow(VolumetricFlowState())
    val state: StateFlow<VolumetricFlowState> = _state.asStateFlow()

    fun onAreaChange(v: String) { _state.value = _state.value.copy(area = v) }
    fun onVelocityChange(v: String) { _state.value = _state.value.copy(velocity = v) }

    fun calculate() {
        val a = _state.value.area.replace(",", ".").toDoubleOrNull()
        val v = _state.value.velocity.replace(",", ".").toDoubleOrNull()
        if (a == null || v == null) {
            _state.value = _state.value.copy(error = "Preencha todos os campos corretamente.", result = "")
            return
        }
        val q = a * v
        val result = "Q = %.4f m³/s\n= %.2f m³/h\n= %.2f L/min\n= %.2f L/s".format(
            q, q * 3600, q * 60000, q * 1000
        )
        _state.value = _state.value.copy(result = result, error = "")
    }

    fun clear() { _state.value = VolumetricFlowState() }
}
