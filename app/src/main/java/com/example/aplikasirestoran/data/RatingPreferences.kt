package com.example.aplikasirestoran.data

import android.content.Context

class RatingPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("rating_prefs", Context.MODE_PRIVATE)

    // Simpan rating per menuId
    fun getRating(menuId: Int): Int = prefs.getInt("rating_$menuId", 0)

    fun setRating(menuId: Int, rating: Int) {
        prefs.edit().putInt("rating_$menuId", rating).apply()
    }
}