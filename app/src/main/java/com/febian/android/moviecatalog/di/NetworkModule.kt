package com.febian.android.moviecatalog.di

import com.febian.android.moviecatalog.BuildConfig
import com.febian.android.moviecatalog.data.source.remote.api.ApiInterface
import com.febian.android.moviecatalog.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        @Provides
        @Singleton
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
                HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return logging
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
            okHttpClient.addInterceptor(logging)
            return okHttpClient.build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun provideApi(retrofit: Retrofit): ApiInterface =
            retrofit.create(ApiInterface::class.java)
    }
}