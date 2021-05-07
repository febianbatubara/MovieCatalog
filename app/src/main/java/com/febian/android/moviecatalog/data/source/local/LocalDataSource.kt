package com.febian.android.moviecatalog.data.source.local

import androidx.lifecycle.LiveData
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.source.local.room.CatalogDao

class LocalDataSource private constructor(private val mCatalogDao: CatalogDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogDao: CatalogDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogDao)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mCatalogDao.getMovies()

    fun getAllTvShows(): LiveData<List<TvShowEntity>> = mCatalogDao.getTvShows()

    fun getFavoritedMovies(): LiveData<List<MovieEntity>> = mCatalogDao.getFavoritedMovies()

    fun getFavoritedTvShows(): LiveData<List<TvShowEntity>> = mCatalogDao.getFavoritedTvShows()

    fun insertMovies(movies: List<MovieEntity>) = mCatalogDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = mCatalogDao.insertTvShows(tvShows)
}