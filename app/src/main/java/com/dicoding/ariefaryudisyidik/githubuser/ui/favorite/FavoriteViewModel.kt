package com.dicoding.ariefaryudisyidik.githubuser.ui.favorite

import androidx.lifecycle.ViewModel
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository

class FavoriteViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getFavoriteUser() =
        userRepository.getFavoriteUser()
}