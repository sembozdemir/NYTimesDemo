package com.sembozdemir.nytimesdemo.main

sealed class MainState {
    data class Success(val data: List<String> = emptyList()) : MainState()
    data class Error(val errorMessage: String = "") : MainState()
    object Loading : MainState()
}