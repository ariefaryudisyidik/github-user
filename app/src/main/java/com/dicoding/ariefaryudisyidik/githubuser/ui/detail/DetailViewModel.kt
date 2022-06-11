package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import androidx.lifecycle.*
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.UserDetailsResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<Result<UserDetailsResponse>>()
    val users: LiveData<Result<UserDetailsResponse>> = _users

    fun getUserDetails(username: String) =
        viewModelScope.launch {
            userRepository.getUserDetails(username).asFlow().collect {
                _users.postValue(it)
            }
        }

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