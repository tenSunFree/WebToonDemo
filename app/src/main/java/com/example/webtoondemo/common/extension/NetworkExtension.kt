@file:Suppress("NOTHING_TO_INLINE")
package com.example.webtoondemo.common.extension

import com.example.webtoondemo.common.remote.Result
import com.example.webtoondemo.common.remote.NetworkResult
import org.json.JSONObject

val networkFailedException: Exception
    get() = IllegalStateException("Network Not Success")

fun <T : Any> NetworkResult.safeApi(convert: (String) -> T): Result<T> = safeAPi(this, convert)

inline fun NetworkResult.mapJson(): Result<JSONObject> =
    safeAPi(this) { response ->
        JSONObject(response)
    }

fun <T : Any> safeAPi(result: NetworkResult, convert: (String) -> T): Result<T> {
    return when (result) {
        is NetworkResult.Success -> {
            runCatching {
                Result.Success(convert(result.response))
            }.getOrElse { e ->
                Result.Error(e)
            }
        }
        else -> Result.Error(networkFailedException)
    }
}
