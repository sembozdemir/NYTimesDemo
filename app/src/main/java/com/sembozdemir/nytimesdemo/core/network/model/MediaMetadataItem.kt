package com.sembozdemir.nytimesdemo.core.network.model

import com.squareup.moshi.Json

data class MediaMetadataItem(

    @field:Json(name = "format")
    val format: String? = null,

    @field:Json(name = "width")
    val width: Int? = null,

    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "height")
    val height: Int? = null
)