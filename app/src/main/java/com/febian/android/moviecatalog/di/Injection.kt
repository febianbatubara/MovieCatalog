package com.febian.android.moviecatalog.di

import android.content.Context
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.LocalDataSource
import com.febian.android.moviecatalog.data.source.local.room.CatalogDatabase
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource
import com.febian.android.moviecatalog.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): CatalogRepository {

        val database = CatalogDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogDao())
        val appExecutors = AppExecutors()

        return CatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}