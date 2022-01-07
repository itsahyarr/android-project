package com.example.infowisatajogja.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.100.250/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): ApiClient {
        return getRetrofitClient().create(ApiClient::class.java)
    }
}