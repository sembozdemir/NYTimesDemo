package com.sembozdemir.nytimesdemo.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sembozdemir.nytimesdemo.R
import com.sembozdemir.nytimesdemo.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayoutResId() = R.layout.activity_main

    override fun createViewModel() = provideViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeState()

        viewModel.fetchSomething()
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is MainState.Loading -> {
                    // TODO: show loading
                    Timber.d("Loading")
                }
                is MainState.Error -> {
                    // TODO: show error
                    Timber.d("Error: ${state.errorMessage}")
                }
                is MainState.Success -> {
                    mainTextView.text = state.data.first().title
                }
            }
        })
    }
}
