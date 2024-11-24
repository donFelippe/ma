package com.example.filipapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filipapp.data.SheetMusic
import com.example.filipapp.ui.components.CategoryChip
import com.example.filipapp.ui.components.SheetMusicItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetMusicScreen(sheetMusicList: List<SheetMusic>,
                     onAddClick: () -> Unit,
                     onEditClick: (SheetMusic) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Sheet Music", style = TextStyle(fontSize = 20.sp)) },
            actions = {
                IconButton(onClick = onAddClick) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add"
                    )
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(5) {
                CategoryChip(category = "Category")
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(sheetMusicList.size) { index ->
                SheetMusicItem(sheetMusic = sheetMusicList[index], onEditClick = onEditClick)
            }
        }
    }
}