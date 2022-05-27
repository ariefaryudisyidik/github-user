package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.ApiConfig
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.User
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.UserResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _user = MutableLiveData<List<User>>()
    val user: LiveData<List<User>> = _user

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> = _progressBar

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        viewModelScope.launch {
            delay(1)
            _isLoading.value = false
        }
    }

    fun searchUsers(query: String) {
        _progressBar.value = true
        val client = ApiConfig.getApiService().getUser(query)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _progressBar.value = false
                    _user.postValue(response.body()?.user)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _progressBar.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}