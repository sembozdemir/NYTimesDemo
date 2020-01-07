package com.sembozdemir.nytimesdemo.core.network.model

import com.squareup.moshi.Json

data class NytResponse(

    @field:Json(name = "copyright")
    val copyright: String? = null,

    @field:Json(name = "results")
    val results: List<ResultsItem?>? = null,

    @field:Json(name = "num_results")
    val numResults: Int? = null,

    @field:Json(name = "status")
    val status: String? = null
)