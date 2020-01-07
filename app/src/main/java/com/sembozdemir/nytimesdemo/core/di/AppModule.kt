package com.sembozdemir.nytimesdemo.core.di

import android.content.Context
import com.sembozdemir.nytimesdemo.App
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: App): Context = application.applicationContext
}