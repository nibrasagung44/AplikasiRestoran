package com.example.aplikasirestoran.data

import android.content.Context
import android.content.SharedPreferences

class RestaurantPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFS_NAME = "restaurant_prefs"
        private const val KEY_NAME = "restaurant_name"
        private const val KEY_ADDRESS = "restaurant_address"
        private const val KEY_DESCRIPTION = "restaurant_description"
        private const val KEY_HOURS = "restaurant_hours"

        // Data default restoran fiktif
        private const val DEFAULT_NAME = "Warung Nusantara Bahagia"
        private const val DEFAULT_ADDRESS = "Jl. Pahlawan No. 17, Kediri, Jawa Timur"
        private const val DEFAULT_DESCRIPTION = "Restoran keluarga yang menyajikan cita rasa autentik masakan Nusantara dengan bahan-bahan segar pilihan. Nikmati pengalaman makan yang hangat dan berkesan bersama orang tersayang."
        private const val DEFAULT_HOURS = "Senin - Minggu: 08.00 - 22.00 WIB"
    }

    fun getName(): String = prefs.getString(KEY_NAME, DEFAULT_NAME) ?: DEFAULT_NAME
    fun getAddress(): String = prefs.getString(KEY_ADDRESS, DEFAULT_ADDRESS) ?: DEFAULT_ADDRESS
    fun getDescription(): String = prefs.getString(KEY_DESCRIPTION, DEFAULT_DESCRIPTION) ?: DEFAULT_DESCRIPTION
    fun getHours(): String = prefs.getString(KEY_HOURS, DEFAULT_HOURS) ?: DEFAULT_HOURS

    fun saveProfile(name: String, address: String, description: String, hours: String) {
        prefs.edit().apply {
            putString(KEY_NAME, name)
            putString(KEY_ADDRESS, address)
            putString(KEY_DESCRIPTION, description)
            putString(KEY_HOURS, hours)
            apply()
        }
    }

    data class RestaurantProfile(
        val name: String,
        val address: String,
        val description: String,
        val hours: String
    )

    fun getProfile(): RestaurantProfile = RestaurantProfile(
        name = getName(),
        address = getAddress(),
        description = getDescription(),
        hours = getHours()
    )
}