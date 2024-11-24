package com.example.filipapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import com.example.filipapp.R
import com.example.filipapp.data.SheetMusic

@Composable
fun SheetMusicItem(sheetMusic: SheetMusic, onEditClick: (SheetMusic) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onEditClick(sheetMusic) },
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(sheetMusic.pictureUrl)
                    .crossfade(true)
                    .error(R.drawable.ic_launcher_foreground)
                    .listener(
                        onSuccess = { _, _ -> println("Coil: Image loaded successfully!") },
                        onError = { request, throwable ->
                            println("Coil: Failed to load image. Error: ${throwable}")
                        }
                    )
                    .build(),
                contentDescription = sheetMusic.title,
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = sheetMusic.title,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}