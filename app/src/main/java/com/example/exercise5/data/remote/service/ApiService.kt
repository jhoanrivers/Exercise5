package com.example.exercise5.data.remote.service

import com.example.exercise5.data.remote.model.ResponseMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<ResponseMovie>

}