package com.example.exercise5.data.remote.repository

import com.example.exercise5.data.remote.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovie(
        page: Int
    ) : Flow<NetworkResult<List<MovieModel>>>


}