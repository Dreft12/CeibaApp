package com.jorgealdana.ceibaapp.network

import com.jorgealdana.ceibaapp.models.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    suspend fun getUsers(): Response<List<User>>
}