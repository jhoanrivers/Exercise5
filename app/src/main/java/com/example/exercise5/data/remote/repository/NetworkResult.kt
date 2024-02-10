package com.example.exercise5.data.remote.repository

sealed class NetworkResult<out T>

class SuccessResult<out T>(val data: T) : NetworkResult<T>()

class ErrorResult(val message: String): NetworkResult<Nothing>()