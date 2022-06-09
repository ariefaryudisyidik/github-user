package com.dicoding.ariefaryudisyidik.githubuser.mapper

import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items

object Mapper {

    fun mapResponseToEntity(items: Items) = UserEntity(
        items.login,
        items.avatarUrl,
        items.userUrl
    )

    fun mapResponsesToEntities(input: List<Items>): List<UserEntity> {
        return input.map { mapResponseToEntity(it) }
    }
}