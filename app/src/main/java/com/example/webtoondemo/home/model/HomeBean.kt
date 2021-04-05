package com.example.webtoondemo

import com.example.webtoondemo.home.model.HomeStatus
import java.io.Serializable

data class HomeInfo(
    val id: String,
    val title: String,
    val image: String,
    val writer: String = "",
    val rate: Double = 0.0,
    val updateDate: String = "",
    val homeStatus: HomeStatus = HomeStatus.NONE,
    val isAdult: Boolean = false
) : Serializable {
    var isLock: Boolean = false
}

data class HomeInfoWithFavorite(
    val info: HomeInfo,
    val isFavorite: Boolean = false
) : Serializable
