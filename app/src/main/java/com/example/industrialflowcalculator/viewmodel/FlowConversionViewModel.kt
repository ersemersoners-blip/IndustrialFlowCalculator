package com.example.industrialflowcalculator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class FlowConversionState(
    val value: String = "",
    val fromUnit: String = "m³/h",
    val result: String = "",
    val error: String = ""
)

class FlowConversionViewModel : ViewModel() {
    val units = listOf("m³/h", "L/min", "L/s", "GPM")

    private val _state = MutableStateFlow(FlowConversionState())
    val state: StateFlow<FlowConversionState> = _state.asStateFlow()

    fun onValueChange(v: String) { _state.value = _state.value.copy(value = v) }
    fun onFromUnitChange(v: String) { _state.value = _state.value.copy(fromUnit = v) }

    fun calculate() {
        val v = _state.value.value.replace(",", ".").toDoubleOrNull()
        if (v == null) {
            _state.value = _state.value.copy(error = "Informe um valor válido.", result = "")
            return
        }
        // Convert to m³/s first
        val inMs = when (_state.value.fromUnit) {
            "m³/h" -> v / 3600.0
            "L/min" -> v / 60000.0
            "L/s" -> v / 1000.0
            "GPM" -> v * 6.30902e-5
            else -> v / 3600.0
        }
        val result = "m³/h: %.4f\nL/min: %.4f\nL/s: %.4f\nGPM: %.4f\nm³/s: %.6f".format(
            inMs * 3600, inMs * 60000, inMs * 1000, inMs / 6.30902e-5, inMs
        )
        _state.value = _state.value.copy(result = result, error = "")
    }

    fun clear() { _state.value = FlowConversionState() }
}
