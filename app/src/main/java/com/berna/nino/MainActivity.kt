package com.berna.nino

import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.berna.nino.service.PlaybackService
import com.berna.nino.ui.screens.LibraryScreen
import com.berna.nino.ui.screens.PlayerScreen
import com.berna.nino.ui.theme.NinoTheme
import com.google.common.util.concurrent.MoreExecutors

/**
 * Main activity of the application.
 * Handles the high-level navigation and MediaController connection.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NinoTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                
                // Centralized MediaController state
                var controller by remember { mutableStateOf<MediaController?>(null) }

                // Connect to PlaybackService when the app starts
                DisposableEffect(Unit) {
                    val sessionToken = SessionToken(context, ComponentName(context, PlaybackService::class.java))
                    val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
                    
                    controllerFuture.addListener({
                        controller = controllerFuture.get()
                    }, MoreExecutors.directExecutor())

                    onDispose {
                        MediaController.releaseFuture(controllerFuture)
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "library",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("library") {
                            LibraryScreen(
                                controller = controller,
                                onNavigateToPlayer = {
                                    navController.navigate("player")
                                }
                            )
                        }
                        
                        composable("player") {
                            PlayerScreen(
                                controller = controller,
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
