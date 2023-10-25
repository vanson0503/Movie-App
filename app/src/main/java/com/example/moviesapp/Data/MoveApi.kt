package com.example.moviesapp.Data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoveApi {
    @GET("movies?page=1")
    fun getBestMovies(): Call<Data>
    @GET("movies?page=2")
    fun getUpcomingMovies(): Call<Data>

    @GET("movies/{id}")
    fun getProductById(@Path("id") id: Int): Call<MovieDetail>

    @GET("genres")
    fun getCategories():Call<Genres>

}