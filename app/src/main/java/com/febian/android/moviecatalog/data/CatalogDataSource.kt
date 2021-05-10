package com.febian.android.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.vo.Resource

interface CatalogDataSource {

    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getPopularTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoritedMovies(sort: String): LiveData<PagedList<MovieEntity>>

    fun getFavoritedTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}