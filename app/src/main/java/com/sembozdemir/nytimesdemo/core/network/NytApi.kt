package com.sembozdemir.nytimesdemo.core.network

import com.sembozdemir.nytimesdemo.core.network.model.NytResponse
import retrofit2.Response
import retrofit2.http.GET

interface NytApi {

    @GET("/svc/mostpopular/v2/viewed/7.json")
    suspend fun fetchMostViewed(): Response<NytResponse>
}