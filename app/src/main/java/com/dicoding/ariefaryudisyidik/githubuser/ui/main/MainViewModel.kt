package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository
import com.dicoding.ariefaryudisyidik.githubuser.utils.ThemePreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository,
    private val pref: ThemePreferences
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1)
            _isLoading.value = false
        }
    }

    fun searchUser(username: String) =
        userRepository.searchUser(username)

    fun getThemeMode(): LiveData<Boolean> = pref.getThemeMode().asLiveData()

    fun saveThemeMode(isNightModeActive: Boolean) {
        viewModelScope.launch { pref.saveThemeMode(isNightModeActive) }
    }
}