package com.sembozdemir.nytimesdemo.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        viewModel = createViewModel()
    }

    abstract fun createViewModel(): VM

    protected inline fun <reified T : ViewModel> provideViewModel(): T {
        return ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
    }
}