package com.example.exercise5.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise5.data.remote.model.MovieModel
import com.example.exercise5.data.remote.repository.ErrorResult
import com.example.exercise5.data.remote.repository.MovieRepository
import com.example.exercise5.data.remote.repository.SuccessResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository,

): ViewModel() {

    private val _movies = MutableLiveData<List<MovieModel>>()
    val dataMovies: LiveData<List<MovieModel>>  get() = _movies
    val loadingData = MutableLiveData<Boolean>()
    val errorData = MutableLiveData<String>()

    var page: Int = 1


    fun fetchPopularMovie(){
        loadingData.postValue(true)
        viewModelScope.launch {
            try {
                repository.getPopularMovie(page)
                    .collect{
                        when(it){
                            is ErrorResult -> {
                                loadingData.postValue(false)
                                errorData.postValue(it.message)
                            }
                            is SuccessResult -> {
                                loadingData.postValue(false)
                                _movies.value = it.data!!

                            }
                        }
                    }
            } catch (e: Exception) {
                loadingData.postValue(false)
                errorData.postValue(e.message)
            }
        }
    }




}