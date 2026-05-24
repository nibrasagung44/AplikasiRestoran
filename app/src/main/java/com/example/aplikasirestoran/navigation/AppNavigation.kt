package com.example.aplikasirestoran.navigation

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.aplikasirestoran.data.RestaurantPreferences
import com.example.aplikasirestoran.data.RatingPreferences
import com.example.aplikasirestoran.data.menuList
import com.example.aplikasirestoran.ui.screens.*

// Definisi semua rute navigasi
object Routes {
    const val HOME = "home"
    const val MENU = "menu"
    const val DETAIL_MENU = "menu/{menuId}"
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"

    fun detailMenu(menuId: Int) = "menu/$menuId"
}

// Durasi animasi (ms)
private const val ANIM_DURATION = 350

// ── Animasi masuk: slide dari kanan ───────────────────
private fun enterTransition(): EnterTransition =
    slideInHorizontally(tween(ANIM_DURATION)) { fullWidth -> fullWidth } +
            fadeIn(tween(ANIM_DURATION))

// ── Animasi keluar: slide ke kiri ─────────────────────
private fun exitTransition(): ExitTransition =
    slideOutHorizontally(tween(ANIM_DURATION)) { fullWidth -> -fullWidth / 3 } +
            fadeOut(tween(ANIM_DURATION))

// ── Animasi masuk saat pop (kembali): slide dari kiri ─
private fun popEnterTransition(): EnterTransition =
    slideInHorizontally(tween(ANIM_DURATION)) { fullWidth -> -fullWidth / 3 } +
            fadeIn(tween(ANIM_DURATION))

// ── Animasi keluar saat pop: slide ke kanan ───────────
private fun popExitTransition(): ExitTransition =
    slideOutHorizontally(tween(ANIM_DURATION)) { fullWidth -> fullWidth } +
            fadeOut(tween(ANIM_DURATION))

@Composable
fun AppNavigation(
    navController: NavHostController,
    context: Context,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    val restaurantPrefs = RestaurantPreferences(context)
    val ratingPrefs     = RatingPreferences(context)     // ← untuk fitur rating

    NavHost(
        navController    = navController,
        startDestination = Routes.HOME,
        enterTransition  = { enterTransition() },
        exitTransition   = { exitTransition() },
        popEnterTransition  = { popEnterTransition() },
        popExitTransition   = { popExitTransition() }
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                restaurantPrefs = restaurantPrefs,
                isDarkTheme     = isDarkTheme,
                onToggleTheme   = onToggleTheme,
                onNavigateToMenu    = { navController.navigate(Routes.MENU) },
                onNavigateToProfile = { navController.navigate(Routes.PROFILE) }
            )
        }

        composable(Routes.MENU) {
            MenuScreen(
                menuItems         = menuList,
                onNavigateToDetail = { menuId ->
                    navController.navigate(Routes.detailMenu(menuId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route     = Routes.DETAIL_MENU,
            arguments = listOf(navArgument("menuId") { type = NavType.IntType })
        ) { backStackEntry ->
            val menuId   = backStackEntry.arguments?.getInt("menuId") ?: 1
            val menuItem = menuList.find { it.id == menuId }
            if (menuItem != null) {
                DetailMenuScreen(
                    menuItem       = menuItem,
                    ratingPrefs    = ratingPrefs,            // ← kirim ratingPrefs
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                restaurantPrefs  = restaurantPrefs,
                onNavigateToEdit = { navController.navigate(Routes.EDIT_PROFILE) },
                onNavigateBack   = { navController.popBackStack() }
            )
        }

        composable(Routes.EDIT_PROFILE) {
            EditProfileScreen(
                restaurantPrefs = restaurantPrefs,
                onSave   = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}