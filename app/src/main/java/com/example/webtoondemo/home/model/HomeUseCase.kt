package com.example.webtoondemo.home.model

import com.example.webtoondemo.HomeRequest
import com.example.webtoondemo.common.remote.Result
import com.example.webtoondemo.HomeInfo
import java.util.Calendar
import java.util.Locale

interface HomeUseCase {

    val currentTabs: Array<String>

    val todayTabPosition: Int
        get() = (Calendar.getInstance(Locale.getDefault()).get(Calendar.DAY_OF_WEEK) + 5) % 7

    @Throws(Exception::class)
    suspend operator fun invoke(param: HomeRequest): Result<List<HomeInfo>>
}