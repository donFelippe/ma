package com.example.filipapp.data

import java.util.UUID

data class SheetMusic(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val composer: String,
    val difficulty: String,
    val genre: String,
    val uploadDate: String,
    val pictureUrl: String // Updated to hold a URL
)
