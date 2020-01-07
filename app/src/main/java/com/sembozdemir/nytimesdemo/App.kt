package com.sembozdemir.nytimesdemo

import com.sembozdemir.nytimesdemo.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
        .factory()
        .create(this)

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}