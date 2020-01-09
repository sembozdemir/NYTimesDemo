package com.sembozdemir.nytimesdemo.core.extensions

fun Int?.orZero() = this ?: 0

fun Long?.orZero() = this ?: 0L