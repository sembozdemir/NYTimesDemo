package com.sembozdemir.nytimesdemo

import com.sembozdemir.nytimesdemo.core.network.model.MediaItem
import com.sembozdemir.nytimesdemo.core.network.model.MediaMetadataItem
import com.sembozdemir.nytimesdemo.core.network.model.ResultsItem
import com.sembozdemir.nytimesdemo.main.NewsItem
import com.sembozdemir.nytimesdemo.main.NewsItemMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsItemMapperTest {

    @Test
    fun `newsItem should be mapped correctly`() {
        // Arrange
        val expected = NewsItem(
            id = 1L,
            url = "https://nytimes.com/demo",
            bannerUrl = "https://nytimes.com/demo/banner.jpg",
            title = "NyTimes Demo",
            abstract = "NyTimes Abstract text",
            publisher = "by NyTimes",
            date = "01-01-2020"
        )

        val media = MediaItem(
            mediaMetadata = listOf(
                MediaMetadataItem(
                    format = "mediumThreeByTwo440",
                    url = "https://nytimes.com/demo/banner.jpg"
                )
            )
        )
        val resultsItem = ResultsItem(
            id = 1L,
            url = "https://nytimes.com/demo",
            media = listOf(media),
            title = "NyTimes Demo",
            abstractText = "NyTimes Abstract text",
            publishedDate = "01-01-2020",
            byline = "by NyTimes",
            source = "NyTimes"
        )

        val mapper = NewsItemMapper()

        // Act
        val newsItem = mapper.map(resultsItem)

        // Assert
        assertEquals(expected, newsItem)
    }

    @Test
    fun `publisher should be selected correctly when byline is not empty`() {
        // Arrange
        val expectedPublisher = "by The New York Times"
        val resultsItem = ResultsItem(
            "", "",
            "The New York Times",
            1L, emptyList(), "", "", "", "", "", 1L,
            "by The New York Times",
            "", 0
        )
        val mapper = NewsItemMapper()

        // Act
        val newsItem = mapper.map(resultsItem)

        // Assert
        assertEquals(expectedPublisher, newsItem.publisher)
    }

    @Test
    fun `publisher should be selected correctly when byline is empty`() {
        // Arrange
        val expectedPublisher = "The New York Times"
        val resultsItem = ResultsItem(
            "", "",
            "The New York Times",
            1L, emptyList(), "", "", "", "", "", 1L,
            "",
            "", 0
        )
        val mapper = NewsItemMapper()

        // Act
        val newsItem = mapper.map(resultsItem)

        // Assert
        assertEquals(expectedPublisher, newsItem.publisher)
    }
}