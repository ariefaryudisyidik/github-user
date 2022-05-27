package com.dicoding.ariefaryudisyidik.githubuser.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") query: String
    ): Call<UserResponse>
}