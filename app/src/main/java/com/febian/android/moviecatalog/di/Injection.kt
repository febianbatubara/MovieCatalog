package com.febian.android.moviecatalog.di

import com.febian.android.moviecatalog.api.ApiCall
import com.febian.android.moviecatalog.data.source.CatalogRepository
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): CatalogRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiCall())

        return CatalogRepository.getInstance(remoteDataSource)
    }
}