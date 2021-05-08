package com.febian.android.moviecatalog.data

import androidx.lifecycle.LiveData
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.vo.Resource

interface CatalogDataSource {

    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getPopularTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity>

    fun getFavoritedMovies(): LiveData<List<MovieEntity>>

    fun getFavoritedTvShows(): LiveData<List<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}