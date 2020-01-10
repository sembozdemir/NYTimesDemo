package com.sembozdemir.nytimesdemo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sembozdemir.nytimesdemo.core.base.BaseViewModel
import com.sembozdemir.nytimesdemo.util.ErrorHandler
import com.sembozdemir.nytimesdemo.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {

    val state: LiveData<MainState> get() = mutableState

    private val mutableState: MutableLiveData<MainState> = MutableLiveData()

    val navigateToDetailEvent: LiveData<String>
        get() = mutableNavigateToDetailEvent

    private val mutableNavigateToDetailEvent = SingleLiveEvent<String>()

    fun fetchMostViewed() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableState.postValue(MainState.Loading)
            try {
                val data = repository.fetchMostViewed()
                if (data.isEmpty()) {
                    mutableState.postValue(MainState.Error(errorHandler.getEmptyResponseMessage()))
                } else {
                    mutableState.postValue(MainState.Success(data))
                }
            } catch (e: Exception) {
                Timber.e(e)
                mutableState.postValue(MainState.Error(errorHandler.getPrettyMessage(e)))
            }
        }
    }

    fun onNewsItemClick(item: NewsItem) {
        mutableNavigateToDetailEvent.postValue(item.url)
    }

}