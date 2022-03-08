package com.mobiletandil.domain.utils

sealed class ResponseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : ResponseResult<T>()
    class Failure(val exception: Exception) : ResponseResult<Nothing>()
}
