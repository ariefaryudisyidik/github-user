package com.dicoding.ariefaryudisyidik.githubuser.ui.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items

class FollowingViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getFollowing(username: String) =
        userRepository.getFollowing(username)
}