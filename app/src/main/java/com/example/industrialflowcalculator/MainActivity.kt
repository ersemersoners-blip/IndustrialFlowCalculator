package com.example.industrialflowcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.industrialflowcalculator.ui.screens.*
import com.example.industrialflowcalculator.ui.theme.IndustrialFlowCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndustrialFlowCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController) }
                        composable("volumetric") { VolumetricFlowScreen(navController) }
                        composable("circular") { CircularPipeScreen(navController) }
                        composable("mass") { MassFlowScreen(navController) }
                        composable("conversion") { FlowConversionScreen(navController) }
                        composable("velocity") { VelocityScreen(navController) }
                        composable("reynolds") { ReynoldsScreen(navController) }
                    }
                }
            }
        }
    }
}
