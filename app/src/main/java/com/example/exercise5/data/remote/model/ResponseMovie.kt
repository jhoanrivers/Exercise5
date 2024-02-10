package com.example.exercise5.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseMovie(

    @SerializedName("page")
    val page: Int,


    @SerializedName("results")
    val results: List<MovieModel>
)