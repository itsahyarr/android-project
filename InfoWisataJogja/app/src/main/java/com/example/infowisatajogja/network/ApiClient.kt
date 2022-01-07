package com.example.infowisatajogja.network

import android.telecom.Call
import com.example.infowisatajogja.model.ResponseLogin
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
}