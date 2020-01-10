package com.sembozdemir.nytimesdemo.core.di

import android.content.Context
import com.sembozdemir.nytimesdemo.App
import com.sembozdemir.nytimesdemo.core.network.NetworkModule
import com.sembozdemir.nytimesdemo.util.ErrorHandler
import com.sembozdemir.nytimesdemo.util.ErrorHandlerImpl
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

    @Provides
    @Singleton
    fun provideErrorHandler(context: Context): ErrorHandler {
        return ErrorHandlerImpl(context)
    }
}