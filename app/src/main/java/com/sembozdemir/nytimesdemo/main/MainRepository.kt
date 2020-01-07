package com.sembozdemir.nytimesdemo.main

import javax.inject.Inject

class MainRepository @Inject constructor() {

    // TODO: make something useful
    fun fetchSomething(): List<String> {
        return listOf("First", "Second", "Third")
    }
}