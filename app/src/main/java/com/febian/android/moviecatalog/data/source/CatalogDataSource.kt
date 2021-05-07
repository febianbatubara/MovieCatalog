package com.febian.android.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.TvShowEntity

interface CatalogDataSource {

    fun getPopularMovies(): LiveData<List<MovieEntity>>

    fun getPopularTvShows(): LiveData<List<TvShowEntity>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity>
}