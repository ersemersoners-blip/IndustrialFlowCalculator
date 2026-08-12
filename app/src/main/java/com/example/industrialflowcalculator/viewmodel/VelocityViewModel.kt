package com.example.industrialflowcalculator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.PI

data class VelocityState(
    val flowRate: String = "",
    val flowUnit: String = "m³/h",
    val diameter: String = "",
    val result: String = "",
    val error: String = ""
)

class VelocityViewModel : ViewModel() {
    val flowUnits = listOf("m³/h", "L/min", "L/s", "m³/s")

    private val _state = MutableStateFlow(VelocityState())
    val state: StateFlow<VelocityState> = _state.asStateFlow()

    fun onFlowRateChange(v: String) { _state.value = _state.value.copy(flowRate = v) }
    fun onFlowUnitChange(v: String) { _state.value = _state.value.copy(flowUnit = v) }
    fun onDiameterChange(v: String) { _state.value = _state.value.copy(diameter = v) }

    fun calculate() {
        val q = _state.value.flowRate.replace(",", ".").toDoubleOrNull()
        val d = _state.value.diameter.replace(",", ".").toDoubleOrNull()
        if (q == null || d == null || d <= 0) {
            _state.value = _state.value.copy(error = "Preencha todos os campos corretamente.", result = "")
            return
        }
        val qMs = when (_state.value.flowUnit) {
            "m³/h" -> q / 3600.0
            "L/min" -> q / 60000.0
            "L/s" -> q / 1000.0
            else -> q
        }
        val r = (d / 1000.0) / 2.0
        val a = PI * r * r
        if (a == 0.0) {
            _state.value = _state.value.copy(error = "Diâmetro inválido.", result = "")
            return
        }
        val v = qMs / a
        val result = "Área: %.6f m²\nVelocidade: %.4f m/s\n= %.2f km/h".format(a, v, v * 3.6)
        _state.value = _state.value.copy(result = result, error = "")
    }

    fun clear() { _state.value = VelocityState() }
}
