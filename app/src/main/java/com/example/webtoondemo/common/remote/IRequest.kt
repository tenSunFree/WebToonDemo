package com.example.webtoondemo.common.remote

import android.net.Uri
import com.example.webtoondemo.common.extension.toFormBody
import okhttp3.Request

data class IRequest(

    val method: REQUEST_METHOD = REQUEST_METHOD.GET,

    val url: String,

    val params: Map<String, String> = emptyMap(),

    val headers: Map<String, String> = emptyMap()
)

@Throws(Exception::class)
fun IRequest.buildRequestApi(): Request {
    val builder = Request.Builder().apply {
        for ((key, value) in this@buildRequestApi.headers) {
            addHeader(key, value)
        }

        when (this@buildRequestApi.method) {
            REQUEST_METHOD.POST -> {
                post(this@buildRequestApi.params.toFormBody())
                url(this@buildRequestApi.url)
            }
            REQUEST_METHOD.GET -> {
                val url = Uri.Builder().encodedPath(this@buildRequestApi.url).apply {
                    for ((key, value) in this@buildRequestApi.params) {
                        appendQueryParameter(key, value)
                    }
                }.build()
                url(url.toString())
            }
        }
    }
    return builder.build()
}
