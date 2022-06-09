package com.dicoding.ariefaryudisyidik.githubuser.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import com.dicoding.ariefaryudisyidik.githubuser.data.local.room.UserDao
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.UserDetailsResponse
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.retrofit.ApiService
import com.dicoding.ariefaryudisyidik.githubuser.mapper.Mapper

class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    fun searchUser(username: String): LiveData<Result<List<UserEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUser(username).items
            emit(Result.Success(Mapper.mapResponsesToEntities(response)))
        } catch (e: Exception) {
            Log.e(TAG, "searchUser: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getUserDetails(username: String): LiveData<Result<UserDetailsResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUserDetails(username)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e(TAG, "searchUser: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFollowers(username: String): LiveData<Result<List<UserEntity>>> =
        liveData {
            try {
                val response = apiService.getFollowers(username)
                emit(Result.Success(Mapper.mapResponsesToEntities(response)))
            } catch (e: Exception) {
                Log.e(TAG, "searchUser: ${e.message.toString()}")
                emit(Result.Error(e.message.toString()))
            }
        }

    fun getFollowing(username: String): LiveData<Result<List<UserEntity>>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getFollowing(username)
                emit(Result.Success(Mapper.mapResponsesToEntities(response)))
            } catch (e: Exception) {
                Log.e(TAG, "searchUser: ${e.message.toString()}")
                emit(Result.Error(e.message.toString()))
            }
        }

//    fun getFavoriteUser(): LiveData<List<UserEntity>> = userDao.getFavoriteUser()

//    suspend fun setFavoriteUser(user: UserEntity, favoriteState: Boolean) {
//        user.isFavorite = favoriteState
//        userDao.updateUser(user)
//    }

    fun checkUser(login: String) = userDao.checkUser(login)
    suspend fun addFavoriteUser(user: UserEntity) = userDao.addFavoriteUser(user)
    suspend fun deleteFavoriteUser(login: String) = userDao.deleteFavoriteUser(login)

    companion object {
        private const val TAG = "UserRepository"
    }
}