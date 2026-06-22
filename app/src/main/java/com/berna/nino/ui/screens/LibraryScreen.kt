package com.berna.nino.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.berna.nino.data.model.Song
import com.berna.nino.ui.theme.NinoTheme

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    
    // Since minSdk is 33, we only need READ_MEDIA_AUDIO
    val permission = Manifest.permission.READ_MEDIA_AUDIO

    // State to track if permission is granted
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Launcher to request permission from the system
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasPermission = isGranted
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Library",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (hasPermission) {
            // Show the song list if permission is granted
            SongList()
        } else {
            // Show a UI to request permission if not granted
            PermissionRequestUI {
                launcher.launch(permission)
            }
        }
    }
}

@Composable
fun SongList() {
    val dummySongs = listOf(
        Song(1, "Like a Rolling Stone", "Bob Dylan", "6:13"),
        Song(2, "Smells Like Teen Spirit", "Nirvana", "5:01"),
        Song(3, "Billie Jean", "Michael Jackson", "4:54"),
        Song(4, "Imagine", "John Lennon", "3:03"),
        Song(5, "Bohemian Rhapsody", "Queen", "5:55"),
        Song(6, "Purple Haze", "Jimi Hendrix", "2:51"),
        Song(7, "Hey Jude", "The Beatles", "7:11"),
        Song(8, "Respect", "Aretha Franklin", "2:27"),
        Song(9, "Dancing Queen", "ABBA", "3:51"),
        Song(10, "Good Vibrations", "The Beach Boys", "3:35"),
        Song(11, "Hotel California", "Eagles", "6:31"),
        Song(12, "Stayin' Alive", "Bee Gees", "4:45")
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dummySongs) { song ->
            SongItem(song = song)
        }
    }
}

@Composable
fun PermissionRequestUI(onGrantClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "We need access to your music to show your library.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Button(onClick = onGrantClick) {
            Text("Grant Permission")
        }
    }
}

@Composable
fun SongItem(song: Song) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { /* TODO: Play this song */ }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Music icon on the left
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.MusicNote,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Title and artist in the center
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = song.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = song.artist,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Duration on the right
        Text(
            text = song.duration,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    NinoTheme {
        LibraryScreen()
    }
}
