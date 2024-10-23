package com.example.todolistapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color // Tambahkan ini untuk mengimpor Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF87CEEB), // Warna utama (Sky Blue)
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6), // Warna sekunder
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

@Composable
fun ToDoListAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
