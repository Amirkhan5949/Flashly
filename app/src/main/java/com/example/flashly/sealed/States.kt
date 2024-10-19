package com.example.flashly.sealed

sealed class States<T> {
    class Loading<T> : States<T>()
    data class Success<T>(val data: T) : States<T>()
    data class Failure<T>(val error: Throwable) : States<T>()
}