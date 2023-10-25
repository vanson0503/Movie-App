package com.example.moviesapp.Data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : MoveApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://moviesapi.ir/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoveApi::class.java)
    }
}