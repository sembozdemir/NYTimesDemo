package com.sembozdemir.nytimesdemo.main

data class NewsItem(
    val id: Long,
    val url: String = "",
    val bannerUrl: String = "",
    val title: String = "",
    val abstract: String = "",
    val publisher: String = "",
    val date: String = ""
)