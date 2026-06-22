package com.berna.nino.data.repository

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.berna.nino.data.model.Song

/**
 * Repository class responsible for fetching audio files from the device's MediaStore.
 */
class AudioRepository(private val context: Context) {

    /**
     * Queries the MediaStore for audio files and returns a list of [Song] objects.
     */
    fun fetchAudioFiles(): List<Song> {
        val songList = mutableListOf<Song>()
        
        // Define the columns we want to retrieve
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION
        )

        // Select only music files
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

        val query = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn)
                val artist = cursor.getString(artistColumn)
                val duration = cursor.getLong(durationColumn)
                
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                songList.add(Song(id, title, artist, duration, contentUri))
            }
        }
        
        return songList
    }
}
