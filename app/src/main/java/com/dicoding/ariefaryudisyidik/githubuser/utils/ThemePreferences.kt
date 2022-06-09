package com.dicoding.ariefaryudisyidik.githubuser.utils

import android.content.Context

class ThemePreferences(context: Context) {

    private val pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun nightMode() = pref.getBoolean(NIGHT_MODE, false)

    fun nightModeOn() {
        val editor = pref.edit()
        editor.putBoolean(NIGHT_MODE, true)
        editor.apply()
    }

    fun nightModeOff() {
        val editor = pref.edit()
        editor.putBoolean(NIGHT_MODE, false)
        editor.apply()
    }

    companion object {
        private const val PREFS_NAME = "theme_pref"
        private const val NIGHT_MODE = "night_mode"
    }
}