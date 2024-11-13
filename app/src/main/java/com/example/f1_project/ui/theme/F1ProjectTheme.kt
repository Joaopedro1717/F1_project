

package com.example.f1_project.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun F1ProjectTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(), // ou darkColorScheme() para o tema escuro
        typography = Typography,
        content = content
    )
}
