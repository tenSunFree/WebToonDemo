@file:Suppress("NOTHING_TO_INLINE")

package com.example.webtoondemo.common.extension

import com.example.webtoondemo.common.remote.Result
import com.example.webtoondemo.common.remote.NetworkResult
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

inline fun NetworkResult.mapDocument(): Result<Document> =
    safeAPi(this) { response ->
        Jsoup.parse(response)
    }
