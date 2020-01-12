package com.sembozdemir.nytimesdemo.core.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val url = req.url().newBuilder()
            .addQueryParameter("api-key", apiKey)
            .build()
        val res = req.newBuilder().url(url).build()
        return chain.proceed(res)
    }
}