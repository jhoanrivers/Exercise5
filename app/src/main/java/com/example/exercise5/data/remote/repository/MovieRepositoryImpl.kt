package com.example.exercise5.data.remote.repository

import com.example.exercise5.data.remote.model.MovieModel
import com.example.exercise5.data.remote.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class MovieRepositoryImpl
    @Inject constructor(
        private val apiService: ApiService,
        @Named("api_key") private val apiKey: String
    ) : MovieRepository
{
    override suspend fun getPopularMovie(page: Int): Flow<NetworkResult<List<MovieModel>>> {
        return flow {
            try {
                val response = apiService.getPopularMovie(page, apiKey)
                if(response.isSuccessful){
                    emit(SuccessResult(response.body()!!.results))
                } else {
                    throw Exception("Failed load movies")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ErrorResult(e.message!!))

            }

        }.flowOn(Dispatchers.IO)

    }
}