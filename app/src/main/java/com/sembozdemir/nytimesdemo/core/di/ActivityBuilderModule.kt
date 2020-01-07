package com.sembozdemir.nytimesdemo.core.di

import android.app.Application
import com.sembozdemir.nytimesdemo.App
import com.sembozdemir.nytimesdemo.main.MainActivity
import com.sembozdemir.nytimesdemo.main.MainActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuilderModule {

    @Binds
    @Singleton
    abstract fun application(app: App): Application

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}