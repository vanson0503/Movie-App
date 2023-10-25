package com.example.moviesapp.Data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovesViewModel: ViewModel() {
    fun listBestMovies(): LiveData<Data>{
        var data = MutableLiveData<Data>()
        RetrofitInstance.api.getBestMovies()
            .enqueue(object : Callback<Data>{
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if(response.isSuccessful){
                        data?.value = response.body()
                    }
                    else {
                        // Xử lý lỗi khi response không thành công
                        Log.d("ERR", "Response not successful")
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    Log.d("ERR", "Response not successful ${data.toString()}")
                }

            })
        return data
    }
    fun listUpcomingMovies(): LiveData<Data>{
        var data = MutableLiveData<Data>()
        RetrofitInstance.api.getUpcomingMovies()
            .enqueue(object : Callback<Data>{
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if(response.isSuccessful){
                        data?.value = response.body()
                    }
                    else {
                        // Xử lý lỗi khi response không thành công
                        Log.d("ERR", "Response not successful")
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    Log.d("ERR", "Response not successful ${data.toString()}")
                }

            })
        return data
    }

    fun getMovieById(id:Int): LiveData<MovieDetail>{
        var data = MutableLiveData<MovieDetail>()
        RetrofitInstance.api.getProductById(id)
            .enqueue(object : Callback<MovieDetail>{
                override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                    if(response.isSuccessful){
                        data?.value = response.body()
                    }
                    else {
                        // Xử lý lỗi khi response không thành công
                        Log.d("ERR", "Response not successful")
                    }
                }

                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    Log.d("ERR", "Response not successful ${data.toString()}")
                }

            })
        return data
    }

    fun getCategories():LiveData<Genres>{
        var genres = MutableLiveData<Genres>()
        RetrofitInstance.api.getCategories().enqueue(object :Callback<Genres>{
            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                if(response.isSuccessful){
                    genres.value = response.body()
                }
            }

            override fun onFailure(call: Call<Genres>, t: Throwable) {
                Log.d("ERR", "Response not successful ")
            }

        })
        return genres
    }






}