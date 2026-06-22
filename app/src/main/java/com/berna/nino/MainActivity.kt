package com.berna.nino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import com.berna.nino.ui.theme.NinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NinoTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    PlayerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PlayerScreen(modifier: Modifier = Modifier){
    // Our main vertical container
    Column(modifier = modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
        // 1. Album cover placeholder
        Box(modifier = Modifier.size(300.dp).background(Color.DarkGray), contentAlignment = Alignment.Center) {
            Text(text = "Album Art Placeholder", color = Color.White)
        }

        // 2. Song info (Title & Artist)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Song Title", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Artist Name", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
        }

        // 3. Audio Controls
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
            Button(onClick = {/* TODO: Previous Song */}) {
                Text(text = "Previous")
            }
            Button(onClick = {/* TODO: Play/Pause */}) {
                Text(text = "Play")
            }
            Button(onClick = {/* TODO: Next Song */}) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerScreenPreview() {
    NinoTheme {
        PlayerScreen()
    }
}