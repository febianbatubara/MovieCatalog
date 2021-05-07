package com.febian.android.moviecatalog.di

import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): CatalogRepository {
        val remoteDataSource = RemoteDataSource.getInstance()

        return CatalogRepository.getInstance(remoteDataSource)
    }
}