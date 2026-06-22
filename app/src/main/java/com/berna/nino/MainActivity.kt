package com.berna.nino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.berna.nino.ui.screens.PlayerScreen
import com.berna.nino.ui.theme.NinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NinoTheme {
                // Scaffold es la estructura básica de una pantalla de Android
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Llamamos a nuestra pantalla que ahora vive en otro archivo
                    PlayerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
