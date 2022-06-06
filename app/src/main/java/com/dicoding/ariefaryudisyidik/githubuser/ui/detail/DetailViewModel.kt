package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository

class DetailViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun setUserDetails(username: String) =
        userRepository.setUserDetail(username)
}