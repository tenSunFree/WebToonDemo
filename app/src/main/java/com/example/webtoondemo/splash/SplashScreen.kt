package com.example.webtoondemo.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import com.example.webtoondemo.R
import com.google.accompanist.glide.GlideImage
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    isLoading: Boolean,
    onTimeout: () -> Unit
) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    if (!isLoading) {
        LaunchedEffect(true) {
            delay(1000L)
            currentOnTimeout()
        }
    }
    GlideImage(
        modifier = Modifier.fillMaxSize(),
        data = R.drawable.icon_splash,
        contentDescription = null
    )
}