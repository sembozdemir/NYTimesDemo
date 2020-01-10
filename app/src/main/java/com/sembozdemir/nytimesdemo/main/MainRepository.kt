package com.sembozdemir.nytimesdemo.main

import com.sembozdemir.nytimesdemo.core.network.NytApi
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: NytApi,
    private val mapper: NewsItemMapper
) {

    @Throws(Exception::class)
    suspend fun fetchMostViewed(): List<NewsItem> {

        val response = api.fetchMostViewed()

        if (response.isSuccessful) {
            val nytResponse = response.body()
            return nytResponse?.results?.map { mapper.map(it) }.orEmpty()
        }

        return emptyList()
    }
}