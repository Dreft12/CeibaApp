package com.jorgealdana.ceibaapp.network

import com.jorgealdana.ceibaapp.models.Post
import com.jorgealdana.ceibaapp.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/users")
    suspend fun getUsers(): Response<ArrayList<User>>

    @GET("/posts")
    suspend fun getPosts(@Query("userId") userId: Int): Response<ArrayList<Post>>
}