package com.dicoding.ariefaryudisyidik.githubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getFavoriteUser(): LiveData<List<UserEntity>>

    @Query("SELECT count(*) FROM user WHERE user.login = :login")
    fun checkUser(login: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteUser(user: UserEntity)

    @Query("DELETE FROM user WHERE user.login = :login")
    suspend fun deleteFavoriteUser(login: String): Int
}