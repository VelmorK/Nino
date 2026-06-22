package com.berna.nino.data.model

import android.net.Uri

/**
 * Data model for a song.
 * Represents the essential information needed to display and play a song.
 */
data class Song(
    val id: Long,
    val title: String,
    val artist: String,
    val duration: Long,
    val contentUri: Uri
)
