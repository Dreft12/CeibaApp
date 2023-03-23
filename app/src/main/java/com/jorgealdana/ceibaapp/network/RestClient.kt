package com.jorgealdana.ceibaapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient(baseUrl: String) {
    var apiService: ApiService

    init {
        val restAdapter = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = restAdapter.create(ApiService::class.java)
    }
}