package com.example.industrialflowcalculator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MassFlowState(
    val density: String = "",
    val flowRate: String = "",
    val flowUnit: String = "m³/s",
    val result: String = "",
    val error: String = ""
)

class MassFlowViewModel : ViewModel() {
    val flowUnits = listOf("m³/s", "m³/h", "L/min", "L/s")

    private val _state = MutableStateFlow(MassFlowState())
    val state: StateFlow<MassFlowState> = _state.asStateFlow()

    fun onDensityChange(v: String) { _state.value = _state.value.copy(density = v) }
    fun onFlowRateChange(v: String) { _state.value = _state.value.copy(flowRate = v) }
    fun onFlowUnitChange(v: String) { _state.value = _state.value.copy(flowUnit = v) }

    fun calculate() {
        val rho = _state.value.density.replace(",", ".").toDoubleOrNull()
        val q = _state.value.flowRate.replace(",", ".").toDoubleOrNull()
        if (rho == null || q == null) {
            _state.value = _state.value.copy(error = "Preencha todos os campos corretamente.", result = "")
            return
        }
        val qMs = when (_state.value.flowUnit) {
            "m³/h" -> q / 3600.0
            "L/min" -> q / 60000.0
            "L/s" -> q / 1000.0
            else -> q
        }
        val mDot = rho * qMs
        val result = "ṁ = %.4f kg/s\n= %.2f kg/min\n= %.2f kg/h\n= %.2f t/h".format(
            mDot, mDot * 60, mDot * 3600, mDot * 3600 / 1000
        )
        _state.value = _state.value.copy(result = result, error = "")
    }

    fun clear() { _state.value = MassFlowState() }
}
