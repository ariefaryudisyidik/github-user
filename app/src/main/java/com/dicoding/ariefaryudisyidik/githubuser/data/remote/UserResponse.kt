package com.dicoding.ariefaryudisyidik.githubuser.data.remote

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("total_count")
	val totalCount: Int,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean,

	@field:SerializedName("items")
	val user: List<User>
)

data class User(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val username: String,

	@field:SerializedName("html_url")
	val userUrl: String
)
