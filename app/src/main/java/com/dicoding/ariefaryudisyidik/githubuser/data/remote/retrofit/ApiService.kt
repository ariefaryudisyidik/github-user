package com.dicoding.ariefaryudisyidik.githubuser.data.remote.retrofit

import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.UserDetailsResponse
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") username: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetails(
        @Path("username") username: String
    ): Call<UserDetailsResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<Items>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowings(
        @Path("username") username: String
    ): Call<List<Items>>
}