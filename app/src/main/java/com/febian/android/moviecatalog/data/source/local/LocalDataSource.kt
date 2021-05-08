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

    fun getFavoritedMovies(): LiveData<List<MovieEntity>> = mCatalogDao.getFavoritedMovies()

    fun insertMovies(movies: List<MovieEntity>) = mCatalogDao.insertMovies(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        mCatalogDao.updateMovie(movie)
    }

    fun getAllTvShows(): LiveData<List<TvShowEntity>> = mCatalogDao.getTvShows()

    fun getFavoritedTvShows(): LiveData<List<TvShowEntity>> = mCatalogDao.getFavoritedTvShows()

    fun insertTvShows(tvShows: List<TvShowEntity>) = mCatalogDao.insertTvShows(tvShows)

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        mCatalogDao.updateTvShow(tvShow)
    }


}