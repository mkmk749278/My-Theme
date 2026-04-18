package com.mkmk.mytheme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MonoBlack = Color(0xFF000000)
private val MonoNearBlack = Color(0xFF101010)
private val MonoDarkSurface = Color(0xFF1A1A1A)
private val MonoGray = Color(0xFF8A8A8A)
private val MonoLightGray = Color(0xFFE0E0E0)
private val MonoWhite = Color(0xFFFFFFFF)

private val MonochromeDarkScheme: ColorScheme = darkColorScheme(
    primary = MonoWhite,
    onPrimary = MonoBlack,
    secondary = MonoLightGray,
    onSecondary = MonoBlack,
    tertiary = MonoLightGray,
    background = MonoBlack,
    onBackground = MonoWhite,
    surface = MonoDarkSurface,
    onSurface = MonoWhite,
    surfaceVariant = MonoNearBlack,
    onSurfaceVariant = MonoGray
)

private val MonochromeLightScheme: ColorScheme = lightColorScheme(
    primary = MonoBlack,
    onPrimary = MonoWhite,
    secondary = MonoNearBlack,
    onSecondary = MonoWhite,
    tertiary = MonoNearBlack,
    background = MonoWhite,
    onBackground = MonoBlack,
    surface = Color(0xFFF2F2F2),
    onSurface = MonoBlack,
    surfaceVariant = Color(0xFFE6E6E6),
    onSurfaceVariant = Color(0xFF5E5E5E)
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colorScheme = if (isSystemInDarkTheme()) {
        MonochromeDarkScheme
    } else {
        MonochromeLightScheme
    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}
