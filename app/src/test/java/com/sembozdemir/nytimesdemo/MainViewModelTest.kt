package com.sembozdemir.nytimesdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.sembozdemir.nytimesdemo.main.MainRepository
import com.sembozdemir.nytimesdemo.main.MainState
import com.sembozdemir.nytimesdemo.main.MainViewModel
import com.sembozdemir.nytimesdemo.main.NewsItem
import com.sembozdemir.nytimesdemo.util.CoroutineTestRule
import com.sembozdemir.nytimesdemo.util.ErrorHandler
import com.sembozdemir.nytimesdemo.util.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `it should navigate to detail with proper url in case of item click event`() =
        runBlockingTest {
            // Arrange
            val expectedUrl = "https://nytimes.com/dummy"

            val viewModel = MainViewModel(mock(), mock())
            val newsItem = NewsItem(1, expectedUrl, "", "", "", "", "")

            // Act
            viewModel.onNewsItemClick(newsItem)

            // Assert
            viewModel.navigateToDetailEvent.observeOnce { url ->
                assertEquals(expectedUrl, url)
            }
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `state should be error in case of empty response`() = runBlockingTest {
        // Arrange
        val emptyResponseMessage = "Empty response message"
        val expectedState = MainState.Error(emptyResponseMessage)

        val repository = mock<MainRepository>()
        val errorHandler = mock<ErrorHandler>()

        val viewModel = MainViewModel(repository, errorHandler)
        val emptyResponse = emptyList<NewsItem>()

        whenever(repository.fetchMostViewed()).doReturn(emptyResponse)
        whenever(errorHandler.getEmptyResponseMessage()).doReturn(emptyResponseMessage)

        // Act
        viewModel.fetchMostViewed()

        // Assert
        viewModel.state.observeOnce { state ->
            assertEquals(expectedState, state)
            assertEquals(emptyResponseMessage, (state as MainState.Error).errorMessage)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `state should be error in case of any exception`() = runBlockingTest {
        // Arrange
        val expectedState = MainState.Error()

        val repository = mock<MainRepository>()
        val errorHandler = mock<ErrorHandler>()

        val viewModel = MainViewModel(repository, errorHandler)

        whenever(repository.fetchMostViewed()).doThrow(Exception())
        whenever(errorHandler.getPrettyMessage(any())).doReturn("")

        // Act
        viewModel.fetchMostViewed()

        // Assert
        viewModel.state.observeOnce {
            assertEquals(expectedState, MainState.Error())
        }
    }
}