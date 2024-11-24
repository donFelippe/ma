package com.example.filipapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.filipapp.data.SheetMusic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSheetMusicScreen(
    onAdd: (SheetMusic) -> Unit,
    onCancel: () -> Unit
) {
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Sheet Music") },
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
            var title by remember { mutableStateOf("") }
            var composer by remember { mutableStateOf("") }
            var difficulty by remember { mutableStateOf("") }
            var genre by remember { mutableStateOf("") }
            var uploadDate by remember { mutableStateOf("") }
            var pictureUrl by remember { mutableStateOf("") }
            var isError by remember { mutableStateOf(false) }

            // Input Fields
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                isError = isError && title.isEmpty()
            )
            OutlinedTextField(
                value = composer,
                onValueChange = { composer = it },
                label = { Text("Composer") },
                modifier = Modifier.fillMaxWidth(),
                isError = isError && composer.isEmpty()
            )
            OutlinedTextField(
                value = difficulty,
                onValueChange = { difficulty = it },
                label = { Text("Difficulty") },
                modifier = Modifier.fillMaxWidth(),
                isError = isError && difficulty.isEmpty()
            )
            OutlinedTextField(
                value = genre,
                onValueChange = { genre = it },
                label = { Text("Genre") },
                modifier = Modifier.fillMaxWidth(),
                isError = isError && genre.isEmpty()
            )
            OutlinedTextField(
                value = uploadDate,
                onValueChange = { uploadDate = it },
                label = { Text("Upload Date") },
                modifier = Modifier.fillMaxWidth(),
                isError = isError && uploadDate.isEmpty()
            )
            OutlinedTextField(
                value = pictureUrl,
                onValueChange = { pictureUrl = it },
                label = { Text("Picture URL") },
                modifier = Modifier.fillMaxWidth(),
                isError = isError && pictureUrl.isEmpty()
            )

            if (isError) {
                Text(
                    text = "All fields are required",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Action Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if (title.isNotEmpty() && composer.isNotEmpty() && difficulty.isNotEmpty() &&
                            genre.isNotEmpty() && uploadDate.isNotEmpty() && pictureUrl.isNotEmpty()
                        ) {
                            onAdd(
                                SheetMusic(
                                    title = title,
                                    composer = composer,
                                    difficulty = difficulty,
                                    genre = genre,
                                    uploadDate = uploadDate,
                                    pictureUrl = pictureUrl
                                )
                            )
                        } else {
                            isError = true
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Add")
                }
                Button(
                    onClick = { showDeleteConfirmation = true },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Cancel")
                }
            }
        }

        // Confirmation Dialog
        if (showDeleteConfirmation) {
            AlertDialog(
                onDismissRequest = { showDeleteConfirmation = false },
                title = { Text("Confirm Cancel") },
                text = { Text("Are you sure you want to cancel? Any unsaved changes will be lost.") },
                confirmButton = {
                    TextButton(onClick = {
                        showDeleteConfirmation = false
                        onCancel()
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteConfirmation = false }) {
                        Text("No")
                    }   
                }
            )
        }
    }
}
