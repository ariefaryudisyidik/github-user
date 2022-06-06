package com.dicoding.ariefaryudisyidik.githubuser.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import com.dicoding.ariefaryudisyidik.githubuser.data.local.room.UserDao
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.retrofit.ApiService

class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    fun searchUser(username: String): LiveData<Result<List<Items>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUser(username)
            val items = response.items
            emit(Result.Success(items))
        } catch (e: Exception) {
            Log.e(TAG, "searchUser: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        private const val TAG = "UserRepository"
    }
}