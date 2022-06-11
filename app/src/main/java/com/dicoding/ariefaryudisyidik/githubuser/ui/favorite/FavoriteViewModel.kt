package com.dicoding.ariefaryudisyidik.githubuser.ui.favorite

import androidx.lifecycle.*
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<Result<List<UserEntity>>>()
    val users: LiveData<Result<List<UserEntity>>> = _users

    fun getFavoriteUser() =
        viewModelScope.launch {
            userRepository.getFavoriteUser().asFlow().collect {
                _users.postValue(it)
            }
        }
}