package com.example.aplikasirestoran.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary            = OrangePrimary,
    onPrimary          = WarmWhite,
    primaryContainer   = Color(0xFFFFDBCC),
    onPrimaryContainer = OrangeDark,
    secondary          = BrownContainer,
    onSecondary        = WarmWhite,
    secondaryContainer = Color(0xFFD7C2BB),
    onSecondaryContainer = DeepBrown,
    tertiary           = GoldenYellow,
    background         = CreamBackground,
    surface            = WarmWhite,
    onBackground       = DeepBrown,
    onSurface          = DeepBrown,
    surfaceVariant     = Color(0xFFF3E5DC),
    outline            = Color(0xFFCDB8AE),
)

private val DarkColorScheme = darkColorScheme(
    primary            = OrangePrimaryDark,
    onPrimary          = Color(0xFF4A1800),
    primaryContainer   = DarkPrimaryContainer,
    onPrimaryContainer = Color(0xFFFFDBCC),
    secondary          = Color(0xFFE0BFB0),
    onSecondary        = Color(0xFF3B1F14),
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = Color(0xFFE8C9BA),
    tertiary           = GoldenYellow,
    background         = DarkBackground,
    surface            = DarkSurface,
    onBackground       = DarkOnSurface,
    onSurface          = DarkOnSurface,
    surfaceVariant     = Color(0xFF3A2820),
    outline            = Color(0xFF8A6555),
)

@Composable
fun AplikasiRestoranTheme(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = Typography,
        content     = content
    )
}