package com.example.filipapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryChip(category: String) {
    Surface(
        color = Color.Gray,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.White,
            style = TextStyle(fontSize = 14.sp)
        )
    }
}