package com.dicoding.ariefaryudisyidik.githubuser.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}