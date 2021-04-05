package com.example.webtoondemo.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    private val _initLoading = MutableStateFlow(false)
    val loadingStateFlow: StateFlow<Boolean> get() = _initLoading

    init {
        viewModelScope.launch {
            delay(1000L)
            _initLoading.value = true
        }
    }
}
