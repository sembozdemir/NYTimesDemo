package com.sembozdemir.nytimesdemo.main

import android.app.Activity
import com.sembozdemir.nytimesdemo.core.di.ActivityScope
import com.sembozdemir.nytimesdemo.core.di.BaseActivityModule
import dagger.Binds
import dagger.Module

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract fun activity(mainActivity: MainActivity): Activity
}