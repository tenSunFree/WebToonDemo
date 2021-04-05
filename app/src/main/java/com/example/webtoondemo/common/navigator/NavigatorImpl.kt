package com.example.webtoondemo.common.navigator

import android.content.Context
import com.example.webtoondemo.common.extension.startActivity
import com.example.webtoondemo.home.view.HomeActivity
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator {
    override fun startHomeActivity(context: Context) {
        val extraArray = arrayOf("title" to "我的票卷")
        context.startActivity<HomeActivity>(*extraArray)
    }
}