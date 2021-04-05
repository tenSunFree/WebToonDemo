package com.example.webtoondemo.common.remote

sealed class NetworkResult {
    class Success(val response: String) : NetworkResult()
    class Fail(val throwable: Throwable) : NetworkResult()
}
