package com.berna.nino.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.berna.nino.data.model.Song
import com.berna.nino.ui.theme.NinoTheme

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    // Lista de canciones de prueba (Mock data)
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

        // LazyColumn es el motor de la lista eficiente
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 'items' recorre nuestra lista de canciones y dibuja una por una
            items(dummySongs) { song ->
                SongItem(song = song)
            }
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
        // Icono de música a la izquierda
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

        // Título y artista en el centro
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

        // Duración a la derecha
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
