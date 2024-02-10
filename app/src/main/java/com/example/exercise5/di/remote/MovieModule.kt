package com.example.exercise5.di.remote

import com.example.exercise5.data.remote.repository.MovieRepository
import com.example.exercise5.data.remote.repository.MovieRepositoryImpl
import com.example.exercise5.data.remote.service.ApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MovieModule {


    @Provides
    @Singleton
    @Named("api_key")
    fun provideApiKey() : String {
        return "e482f239c0b03ee604f193d5927beb63"
    }


    @Provides
    @Singleton
    fun provideLogging() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton

    fun provideRepository(
        apiService: ApiService,
        @Named("api_key") apiKey: String
    ) : MovieRepository {
        return MovieRepositoryImpl(apiService, apiKey)
    }

}