    package com.example.filipapp.ui.screen

    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.automirrored.filled.ArrowBack
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.TopAppBar
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.unit.dp
    import com.example.filipapp.data.SheetMusic
    import java.util.UUID

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EditSheetMusicScreen(
        sheetMusic: SheetMusic,
        onUpdate: (SheetMusic) -> Unit,
        onDelete: (UUID) -> Unit,
        onCancel: () -> Unit
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Edit Sheet Music") },
                    navigationIcon = {
                        IconButton(onClick = onCancel) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                var title by remember { mutableStateOf(sheetMusic.title) }
                var composer by remember { mutableStateOf(sheetMusic.composer) }
                var difficulty by remember { mutableStateOf(sheetMusic.difficulty) }
                var genre by remember { mutableStateOf(sheetMusic.genre) }
                var uploadDate by remember { mutableStateOf(sheetMusic.uploadDate) }
                var pictureUrl by remember { mutableStateOf(sheetMusic.pictureUrl) }

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = composer,
                    onValueChange = { composer = it },
                    label = { Text("Composer") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = difficulty,
                    onValueChange = { difficulty = it },
                    label = { Text("Difficulty") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = genre,
                    onValueChange = { genre = it },
                    label = { Text("Genre") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uploadDate,
                    onValueChange = { uploadDate = it },
                    label = { Text("Upload Date") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = pictureUrl,
                    onValueChange = { pictureUrl = it },
                    label = { Text("Picture URL") },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            onUpdate(
                                sheetMusic.copy(
                                    title = title,
                                    composer = composer,
                                    difficulty = difficulty,
                                    genre = genre,
                                    uploadDate = uploadDate,
                                    pictureUrl = pictureUrl
                                )
                            )
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Update")
                    }
                    Button(
                        onClick = { onDelete(sheetMusic.id) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Delete", color = Color.White)
                    }
                }
            }
        }
    }