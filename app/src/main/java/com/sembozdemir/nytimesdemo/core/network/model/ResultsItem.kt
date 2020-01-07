package com.sembozdemir.nytimesdemo.core.network.model

import com.squareup.moshi.Json

data class ResultsItem(

    @field:Json(name = "column")
    val column: Any? = null,

    @field:Json(name = "section")
    val section: String? = null,

    @field:Json(name = "abstract")
    val abstractText: String? = null,

    @field:Json(name = "source")
    val source: String? = null,

    @field:Json(name = "asset_id")
    val assetId: Long? = null,

    @field:Json(name = "media")
    val media: List<MediaItem?>? = null,

    @field:Json(name = "type")
    val type: String? = null,

    @field:Json(name = "title")
    val title: String? = null,

    @field:Json(name = "uri")
    val uri: String? = null,

    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "adx_keywords")
    val adxKeywords: String? = null,

    @field:Json(name = "id")
    val id: Long? = null,

    @field:Json(name = "byline")
    val byline: String? = null,

    @field:Json(name = "published_date")
    val publishedDate: String? = null,

    @field:Json(name = "views")
    val views: Int? = null
)