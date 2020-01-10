package com.sembozdemir.nytimesdemo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sembozdemir.nytimesdemo.core.base.BaseViewModel
import com.sembozdemir.nytimesdemo.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    val state: LiveData<MainState> get() = mutableState

    private val mutableState: MutableLiveData<MainState> = MutableLiveData()

    val navigateToDetailEvent: LiveData<String>
        get() = mutableNavigateToDetailEvent

    private val mutableNavigateToDetailEvent = SingleLiveEvent<String>()

    fun fetchMostViewed() {
        mutableState.postValue(MainState.Loading)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val data = repository.fetchMostViewed()
                    mutableState.postValue(MainState.Success(data))
                } catch (e: Exception) {
                    Timber.e(e)
                    mutableState.postValue(MainState.Error("Something went wrong"))
                }
            }
        }
    }

    fun onNewsItemClick(item: NewsItem) {
        mutableNavigateToDetailEvent.postValue(item.url)
    }

}