package com.example.industrialflowcalculator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ReynoldsState(
    val density: String = "",
    val velocity: String = "",
    val diameter: String = "",
    val viscosity: String = "",
    val result: String = "",
    val regime: String = "",
    val error: String = ""
)

class ReynoldsViewModel : ViewModel() {
    private val _state = MutableStateFlow(ReynoldsState())
    val state: StateFlow<ReynoldsState> = _state.asStateFlow()

    fun onDensityChange(v: String) { _state.value = _state.value.copy(density = v) }
    fun onVelocityChange(v: String) { _state.value = _state.value.copy(velocity = v) }
    fun onDiameterChange(v: String) { _state.value = _state.value.copy(diameter = v) }
    fun onViscosityChange(v: String) { _state.value = _state.value.copy(viscosity = v) }

    fun calculate() {
        val rho = _state.value.density.replace(",", ".").toDoubleOrNull()
        val v = _state.value.velocity.replace(",", ".").toDoubleOrNull()
        val d = _state.value.diameter.replace(",", ".").toDoubleOrNull()
        val mu = _state.value.viscosity.replace(",", ".").toDoubleOrNull()
        if (rho == null || v == null || d == null || mu == null || mu == 0.0 || d <= 0) {
            _state.value = _state.value.copy(error = "Preencha todos os campos corretamente.", result = "", regime = "")
            return
        }
        val dM = d / 1000.0
        val re = (rho * v * dM) / mu
        val regime = when {
            re < 2000 -> "🟢 LAMINAR (Re < 2000)"
            re < 4000 -> "🟡 TRANSIÇÃO (2000 ≤ Re < 4000)"
            else -> "🔴 TURBULENTO (Re ≥ 4000)"
        }
        val result = "Re = %.0f".format(re)
        _state.value = _state.value.copy(result = result, regime = regime, error = "")
    }

    fun clear() { _state.value = ReynoldsState() }
}
