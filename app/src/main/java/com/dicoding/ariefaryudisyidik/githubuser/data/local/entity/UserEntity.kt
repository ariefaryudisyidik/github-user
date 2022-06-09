package com.dicoding.ariefaryudisyidik.githubuser.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserEntity(
    @field:PrimaryKey
    val login: String,
    val avatarUrl: String,
    val userUrl: String
) : Parcelable