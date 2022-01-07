package com.example.infowisatajogja.network

import com.example.infowisatajogja.model.ResponseLogin
import com.example.infowisatajogja.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {
    @FormUrlEncoded
    @POST("dbconfig-jtour/login_service.php")

    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ): Call<ResponseLogin>

    fun register(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password: String
    ): Call<ResponseRegister>
}