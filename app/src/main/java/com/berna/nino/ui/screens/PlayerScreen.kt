package com.berna.nino.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.berna.nino.ui.theme.NinoTheme

@Composable
fun PlayerScreen(modifier: Modifier = Modifier) {
    // Estado temporal para el slider
    var sliderPosition by remember { mutableFloatStateOf(0.3f) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // 1. Album cover
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Album Art",
                color = Color.White.copy(alpha = 0.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // 2. Song info
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Song Title",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Artist Name",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // 3. Progress Slider
        Column(modifier = Modifier.fillMaxWidth()) {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "1:24", style = MaterialTheme.typography.labelSmall)
                Text(text = "3:45", style = MaterialTheme.typography.labelSmall)
            }
        }

        // 4. Audio Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO */ }, modifier = Modifier.size(48.dp)) {
                Icon(
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = "Previous",
                    modifier = Modifier.size(36.dp)
                )
            }

            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .size(72.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            IconButton(onClick = { /* TODO */ }, modifier = Modifier.size(48.dp)) {
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "Next",
                    modifier = Modifier.size(36.dp)
                )
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
