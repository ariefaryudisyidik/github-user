package com.dicoding.ariefaryudisyidik.githubuser.ui.followers

import androidx.lifecycle.ViewModel
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository

class FollowersViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getFollowers(username: String) =
        userRepository.getFollowers(username)
}