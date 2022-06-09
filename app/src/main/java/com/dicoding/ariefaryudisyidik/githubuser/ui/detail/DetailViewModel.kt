package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import kotlinx.coroutines.launch

class DetailViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUserDetails(username: String) =
        userRepository.getUserDetails(username)

    fun checkUser(login: String) = userRepository.checkUser(login)

    fun addFavoriteUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.addFavoriteUser(user)
        }
    }

    fun deleteFavoriteUser(login: String) {
        viewModelScope.launch {
            userRepository.deleteFavoriteUser(login)
        }
    }
}