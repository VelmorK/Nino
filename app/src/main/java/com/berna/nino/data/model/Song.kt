package com.berna.nino.data.model

/**
 * Data model for a song.
 * Represents the essential information needed to display a song in the UI.
 */
data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val duration: String
)
