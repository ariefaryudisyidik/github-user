package com.dicoding.ariefaryudisyidik.githubuser.ui.followers

import androidx.lifecycle.*
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FollowersViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<Result<List<UserEntity>>>()
    val users: LiveData<Result<List<UserEntity>>> = _users

    fun getFollowers(username: String) =
        viewModelScope.launch {
            userRepository.getFollowers(username).asFlow().collect {
                _users.postValue(it)
            }
        }
}