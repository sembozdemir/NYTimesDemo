package com.sembozdemir.nytimesdemo.core.di

import android.content.Context
import com.sembozdemir.nytimesdemo.App
import com.sembozdemir.nytimesdemo.core.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: App): Context = application.applicationContext
}