package com.example.webtoondemo.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.webtoondemo.*
import com.example.webtoondemo.common.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel by viewModels<SplashViewModel>()

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComposeView {
            val isLoading by viewModel.loadingStateFlow.collectAsState(false)
            SplashScreen(isLoading = !isLoading) { startHomeActivity() }
        }
    }

    private fun startHomeActivity() {
        navigator.startHomeActivity(this)
        finish()
    }
}
