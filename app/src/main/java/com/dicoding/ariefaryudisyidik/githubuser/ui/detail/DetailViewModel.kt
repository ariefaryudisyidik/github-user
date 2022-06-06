package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository

class DetailViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUserDetails(username: String) =
        userRepository.getUserDetails(username)
}