package com.example.webtoondemo.home.model

sealed class HomeEvent {
    class ErrorEvent(val message: String) : HomeEvent()
}
