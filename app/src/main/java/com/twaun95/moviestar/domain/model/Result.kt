package com.twaun95.moviestar.domain.model

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Fail(val eMessage: String) : Result<Nothing>()
}