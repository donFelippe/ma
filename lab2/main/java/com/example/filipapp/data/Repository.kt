package com.example.filipapp.data

import androidx.compose.runtime.mutableStateListOf
import java.util.UUID

class SheetMusicRepository {
    private val sheetMusicList = mutableStateListOf<SheetMusic>()

    fun getAll(): List<SheetMusic> = sheetMusicList

    fun add(sheetMusic: SheetMusic) {
        sheetMusicList.add(sheetMusic)
    }

    fun update(sheetMusic: SheetMusic) {
        val index = sheetMusicList.indexOfFirst { it.id == sheetMusic.id }
        if (index != -1) {
            sheetMusicList[index] = sheetMusic
        }
    }

    fun delete(sheetMusicId: UUID) {
        sheetMusicList.removeAll { it.id == sheetMusicId }
    }
}
