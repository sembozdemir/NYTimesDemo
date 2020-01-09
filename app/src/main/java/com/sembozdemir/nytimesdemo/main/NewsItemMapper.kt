package com.sembozdemir.nytimesdemo.main

import com.sembozdemir.nytimesdemo.core.extensions.orZero
import com.sembozdemir.nytimesdemo.core.network.model.ResultsItem
import javax.inject.Inject

private const val DEFAULT_MEDIA_FORMAT = "mediumThreeByTwo440"

class NewsItemMapper @Inject constructor() {

    fun map(resultsItem: ResultsItem?): NewsItem {

        val bannerUrl: String = resultsItem?.media?.firstOrNull()
            ?.mediaMetadata?.firstOrNull { it?.format == DEFAULT_MEDIA_FORMAT }
            ?.url.orEmpty()

        val publisher: String = resultsItem?.byline.takeUnless { it.isNullOrEmpty() }
            ?: resultsItem?.source.orEmpty()

        return NewsItem(
            id = resultsItem?.id.orZero(),
            url = resultsItem?.url.orEmpty(),
            bannerUrl = bannerUrl,
            title = resultsItem?.title.orEmpty(),
            abstract = resultsItem?.abstractText.orEmpty(),
            publisher = publisher,
            date = resultsItem?.publishedDate.orEmpty()
        )
    }

}
