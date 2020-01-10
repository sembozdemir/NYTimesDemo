package com.sembozdemir.nytimesdemo.util

interface ErrorHandler {
    fun getPrettyMessage(e: Exception): String
    fun getEmptyResponseMessage(): String
}