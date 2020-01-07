package com.sembozdemir.nytimesdemo.core.network

import com.sembozdemir.nytimesdemo.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

private const val NYT_API_BASE_URL = "https://api.nytimes.com"
private const val API_KEY = "NwOZOLuAeAGH70Wm5q3oGno550zqNags"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }

        okHttpBuilder.addInterceptor { chain ->
            val req = chain.request()
            val url = req.url().newBuilder().addQueryParameter("api-key", API_KEY).build()
            chain.proceed(req.newBuilder().url(url).build())
        }

        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(NYT_API_BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideNytApi(retrofit: Retrofit): NytApi = retrofit.create()
}