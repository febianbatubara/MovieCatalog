package com.febian.android.moviecatalog.data.source.remote

import com.febian.android.moviecatalog.api.RetrofitService
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    suspend fun getPopularMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        RetrofitService.apiInterface.getPopularMovies().await().results.let { movieList ->
            callback.onAllMoviesReceived(
                movieList
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getPopularTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        RetrofitService.apiInterface.getPopularTvShows().await().results.let { tvShowList ->
            callback.onAllTvShowsReceived(
                tvShowList
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        RetrofitService.apiInterface.getMovieDetail(movieId).await().let { movie ->
            callback.onMovieDetailReceived(
                movie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        RetrofitService.apiInterface.getTvShowDetail(tvShowId).await().let { tvShow ->
            callback.onTvShowDetailReceived(
                tvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieEntity>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowsResponses: List<TvShowEntity>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MovieEntity)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowDetailResponse: TvShowEntity)
    }
}