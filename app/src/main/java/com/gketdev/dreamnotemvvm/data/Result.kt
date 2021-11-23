package com.gketdev.dreamnotemvvm.data

sealed class Result<out T> {
    class Success<out T>(val data: T) : Result<T>()
    class Error(
        val error: Throwable? = null,
        val code: ErrorCode? = null,
        val message: String? = null
    ) : Result<Nothing>()
}