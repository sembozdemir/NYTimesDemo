package com.sembozdemir.nytimesdemo.core.di


import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class BaseActivityModule {

    @Binds
    @ActivityScope
    abstract fun activityContext(activity: Activity): Context
}
