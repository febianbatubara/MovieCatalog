package com.febian.android.moviecatalog.data.source.remote.api

import com.febian.android.moviecatalog.BuildConfig
import com.febian.android.moviecatalog.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitService {

    private val retrofitService by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ApiInterface by lazy {
        retrofitService
            .create(ApiInterface::class.java)
    }
}