package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import androidx.lifecycle.*
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import com.dicoding.ariefaryudisyidik.githubuser.utils.ThemePreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository,
    private val pref: ThemePreferences
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _users = MutableLiveData<Result<List<UserEntity>>>()
    val users: LiveData<Result<List<UserEntity>>> = _users

    init {
        viewModelScope.launch {
            delay(1)
            _isLoading.value = false
        }
    }

    fun searchUser(username: String) {
        viewModelScope.launch {
            userRepository.searchUser(username).asFlow().collect {
                _users.postValue(it)
            }
        }
    }

    fun getThemeMode(): LiveData<Boolean> = pref.getThemeMode().asLiveData()

    fun saveThemeMode(isNightModeActive: Boolean) {
        viewModelScope.launch { pref.saveThemeMode(isNightModeActive) }
    }
}