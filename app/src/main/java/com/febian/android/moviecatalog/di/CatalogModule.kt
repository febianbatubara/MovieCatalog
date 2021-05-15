package com.febian.android.moviecatalog.di

import android.app.Application
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.LocalDataSource
import com.febian.android.moviecatalog.data.source.local.room.CatalogDao
import com.febian.android.moviecatalog.data.source.local.room.CatalogDatabase
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource
import com.febian.android.moviecatalog.data.source.remote.api.ApiInterface
import com.febian.android.moviecatalog.utils.AppExecutors
import com.febian.android.moviecatalog.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CatalogModule {

    companion object {
        @Singleton
        @Provides
        fun provideDatabase(application: Application): CatalogDatabase {
            return CatalogDatabase.getInstance(application)
        }

        @Singleton
        @Provides
        fun provideDao(catalogDatabase: CatalogDatabase): CatalogDao {
            return catalogDatabase.catalogDao()
        }

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiInterface: ApiInterface): RemoteDataSource {
            return RemoteDataSource(apiInterface)
        }

        @Singleton
        @Provides
        fun provideLocalDataSource(catalogDao: CatalogDao): LocalDataSource {
            return LocalDataSource(catalogDao)
        }

        @Singleton
        @Provides
        fun provideAppExecutor(): AppExecutors {
            return AppExecutors()
        }

        @Singleton
        @Provides
        fun provideRepository(
            localDataSource: LocalDataSource,
            remoteDataSource: RemoteDataSource,
            appExecutors: AppExecutors
        ): CatalogRepository {
            return CatalogRepository(remoteDataSource, localDataSource, appExecutors)
        }

        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepository: CatalogRepository): ViewModelFactory {
            return ViewModelFactory(catalogRepository)
        }
    }
}