package com.sembozdemir.nytimesdemo.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sembozdemir.nytimesdemo.R
import com.sembozdemir.nytimesdemo.core.base.BaseActivity
import com.sembozdemir.nytimesdemo.core.extensions.customTab
import com.sembozdemir.nytimesdemo.util.VerticalSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

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
                }
                is MainState.Error -> {
                    mainSwipeRefreshLayout.isRefreshing = false
                    showError(state.errorMessage)
                }
                is MainState.Success -> {
                    mainSwipeRefreshLayout.isRefreshing = false
                    newsAdapter.updateItems(state.data)
                }
            }
        })
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(mainCoordinatorLayout, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private fun observeNavigationEvent() {
        viewModel.navigateToDetailEvent.observe(this, Observer { url ->
            customTab(url)
        })
    }
}
