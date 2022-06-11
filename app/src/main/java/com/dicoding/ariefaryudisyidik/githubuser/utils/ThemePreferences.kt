package com.dicoding.ariefaryudisyidik.githubuser.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemePreferences(private val context: Context) {

    fun getThemeMode(): Flow<Boolean> = context.dataStore.data.map { it[THEME_KEY] ?: false }

    suspend fun saveThemeMode(isNightModeActive: Boolean) {
        context.dataStore.edit { it[THEME_KEY] = isNightModeActive }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("theme_pref")
        private val THEME_KEY = booleanPreferencesKey("theme_pref")
    }
}