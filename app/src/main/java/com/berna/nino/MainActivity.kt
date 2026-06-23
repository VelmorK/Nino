package com.berna.nino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.berna.nino.ui.screens.LibraryScreen
import com.berna.nino.ui.screens.PlayerScreen
import com.berna.nino.ui.theme.NinoTheme

/**
 * Main activity of the application.
 * Handles the high-level navigation between screens.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NinoTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // NavHost defines the "map" of our application's screens
                    NavHost(
                        navController = navController,
                        startDestination = "library",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Define the Library screen route
                        composable("library") {
                            LibraryScreen(
                                onNavigateToPlayer = {
                                    navController.navigate("player")
                                }
                            )
                        }
                        
                        // Define the Player screen route
                        composable("player") {
                            PlayerScreen()
                        }
                    }
                }
            }
        }
    }
}
