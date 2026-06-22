package com.berna.nino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.berna.nino.ui.screens.LibraryScreen
import com.berna.nino.ui.screens.PlayerScreen
import com.berna.nino.ui.theme.NinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NinoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Cambiamos PlayerScreen por LibraryScreen para probar
                    LibraryScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
