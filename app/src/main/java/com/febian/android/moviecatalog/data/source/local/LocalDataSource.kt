package com.febian.android.moviecatalog.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.source.local.room.CatalogDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mCatalogDao: CatalogDao) {

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mCatalogDao.getMovies()

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> = mCatalogDao.getMovieDetail(movieId)

    fun getFavoritedMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity> =
        mCatalogDao.getFavoritedMovies(query)

    fun insertMovies(movies: List<MovieEntity>) = mCatalogDao.insertMovies(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        mCatalogDao.updateMovie(movie)
    }

    fun updateMovie(movie: MovieEntity) = mCatalogDao.updateMovie(movie)

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = mCatalogDao.getTvShows()

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
        mCatalogDao.getTvShowDetail(tvShowId)

    fun getFavoritedTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowEntity> =
        mCatalogDao.getFavoritedTvShows(query)

    fun insertTvShows(tvShows: List<TvShowEntity>) = mCatalogDao.insertTvShows(tvShows)

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        mCatalogDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvShowEntity) = mCatalogDao.updateTvShow(tvShow)
}