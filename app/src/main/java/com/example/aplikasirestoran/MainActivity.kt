package com.example.aplikasirestoran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.aplikasirestoran.data.ThemePreferences
import com.example.aplikasirestoran.navigation.AppNavigation
import com.example.aplikasirestoran.ui.theme.AplikasiRestoranTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val themePrefs = ThemePreferences(this)

        setContent {
            // State tema — diinisialisasi dari SharedPreferences
            var isDarkTheme by remember { mutableStateOf(themePrefs.isDarkMode()) }

            AplikasiRestoranTheme(isDarkTheme = isDarkTheme) {
                val navController = rememberNavController()
                AppNavigation(
                    navController    = navController,
                    context          = this,
                    isDarkTheme      = isDarkTheme,
                    onToggleTheme    = {
                        isDarkTheme = !isDarkTheme
                        themePrefs.setDarkMode(isDarkTheme)
                    }
                )
            }
        }
    }
}