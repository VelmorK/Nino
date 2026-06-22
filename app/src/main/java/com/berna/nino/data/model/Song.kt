package com.berna.nino.data.model

/**
 * Esta es nuestra "ficha técnica" para cada canción.
 * Usamos 'data class' porque su única función es guardar datos.
 */
data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val duration: String
)
