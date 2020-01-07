package com.sembozdemir.nytimesdemo.core.network.model

import com.squareup.moshi.Json

data class MediaItem(

    @field:Json(name = "copyright")
    val copyright: String? = null,

    @field:Json(name = "media-metadata")
    val mediaMetadata: List<MediaMetadataItem?>? = null,

    @field:Json(name = "subtype")
    val subtype: String? = null,

    @field:Json(name = "caption")
    val caption: String? = null,

    @field:Json(name = "type")
    val type: String? = null,

    @field:Json(name = "approved_for_syndication")
    val approvedForSyndication: Int? = null
)