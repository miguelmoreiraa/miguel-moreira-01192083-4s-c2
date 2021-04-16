package com.example.cachorros

import retrofit2.Call
import retrofit2.http.*

interface ApiCachorros {

    @POST("cachorros")
    fun post(@Body novoCachorro: Cachorros): Call<Cachorros>

}