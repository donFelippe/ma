package com.example.filipapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filipapp.data.SheetMusicRepository
import com.example.filipapp.ui.screen.AddSheetMusicScreen
import com.example.filipapp.ui.screen.EditSheetMusicScreen
import com.example.filipapp.ui.screen.SheetMusicScreen
import java.util.UUID

@Composable
fun SheetMusicApp(repository: SheetMusicRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        // Home screen
        composable(Screen.Home.route) {
            SheetMusicScreen(
                sheetMusicList = repository.getAll(),
                onAddClick = { navController.navigate(Screen.Add.route) },
                onEditClick = { sheetMusic ->
                    navController.navigate(Screen.Edit.createRoute(sheetMusic.id)) // Navigate to Edit
                }
            )
        }

        // Add screen
        composable(Screen.Add.route) {
            AddSheetMusicScreen(
                onAdd = { sheetMusic ->
                    repository.add(sheetMusic)
                    navController.popBackStack() // Go back to the previous screen
                },
                onCancel = { navController.popBackStack() }
            )
        }

        // Edit screen
        composable(Screen.Edit.route) { backStackEntry ->
            val sheetMusicId = backStackEntry.arguments?.getString("sheetMusicId")?.let { UUID.fromString(it) }
            val sheetMusic = repository.getAll().find { it.id == sheetMusicId }

            if (sheetMusic != null) {
                EditSheetMusicScreen(
                    sheetMusic = sheetMusic,
                    onUpdate = { updatedSheetMusic ->
                        repository.update(updatedSheetMusic)
                        navController.popBackStack() // Go back to the previous screen
                    },
                    onDelete = { id ->
                        repository.delete(id)
                        navController.popBackStack() // Go back after deletion
                    },
                    onCancel = { navController.popBackStack() }
                )
            } else {
                Text("Sheet music not found", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Add : Screen("add")
    object Edit : Screen("edit/{sheetMusicId}") { // Dynamic route
        fun createRoute(sheetMusicId: UUID) = "edit/$sheetMusicId" // Helper to build the route
    }
}