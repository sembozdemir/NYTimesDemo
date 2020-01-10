package com.sembozdemir.nytimesdemo.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sembozdemir.nytimesdemo.R
import com.sembozdemir.nytimesdemo.core.base.BaseActivity
import com.sembozdemir.nytimesdemo.core.extensions.customTab
import com.sembozdemir.nytimesdemo.util.VerticalSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>() {

    private val newsAdapter = NewsAdapter {
        viewModel.onNewsItemClick(it)
    }

    override fun getLayoutResId() = R.layout.activity_main

    override fun createViewModel() = provideViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecyclerView()

        setupSwipeToRefresh()

        observeState()

        observeNavigationEvent()

        viewModel.fetchMostViewed()
    }

    private fun setupSwipeToRefresh() {
        mainSwipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchMostViewed()
        }
    }

    private fun setupRecyclerView() {
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.addItemDecoration(
            VerticalSpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.space_default))
        )
        mainRecyclerView.adapter = newsAdapter
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is MainState.Loading -> {
                    mainSwipeRefreshLayout.isRefreshing = true
                    Timber.d("Loading")
                }
                is MainState.Error -> {
                    // TODO: show error
                    mainSwipeRefreshLayout.isRefreshing = false
                    Timber.e("Error: ${state.errorMessage}")
                }
                is MainState.Success -> {
                    mainSwipeRefreshLayout.isRefreshing = false
                    val items = state.data
                    if (items.isNotEmpty()) {
                        newsAdapter.updateItems(state.data)
                    } else {
                        // TODO: show empty message
                        Timber.e("Empty response")
                    }
                }
            }
        })
    }

    private fun observeNavigationEvent() {
        viewModel.navigateToDetailEvent.observe(this, Observer { url ->
            customTab(url)
        })
    }
}
