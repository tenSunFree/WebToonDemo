package com.example.webtoondemo.common.remote

import okhttp3.OkHttpClient
import okhttp3.Request

internal class NetworkUseCase(
    private val okHttpClient: OkHttpClient
) : INetworkUseCase {

    override suspend fun requestApi(request: IRequest): NetworkResult {
        return requestApi(request.buildRequestApi())
    }

    private suspend fun requestApi(request: Request): NetworkResult {
        return runCatching {
            createTask().requestApi(request)
        }.getOrElse { e ->
            NetworkResult.Fail(e)
        }
    }

    private fun createTask(): NetworkTask =
        NetworkTask(okHttpClient)
}

interface INetworkUseCase {
    suspend fun requestApi(request: IRequest): NetworkResult
}
