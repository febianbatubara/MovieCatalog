package com.febian.android.moviecatalog.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.source.remote.api.RetrofitService
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

    suspend fun getPopularMovies(): LiveData<ApiResponse<List<MovieEntity>>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<ApiResponse<List<MovieEntity>>>()
        RetrofitService.apiInterface.getPopularMovies().await().results.let { movieList ->
            movieResults.value = ApiResponse.success(movieList)
            EspressoIdlingResource.decrement()
        }
        return movieResults
    }

    suspend fun getPopularTvShows(): LiveData<ApiResponse<List<TvShowEntity>>> {
        EspressoIdlingResource.increment()
        val tvShowResults = MutableLiveData<ApiResponse<List<TvShowEntity>>>()
        RetrofitService.apiInterface.getPopularTvShows().await().results.let { tvShowList ->
            tvShowResults.value = ApiResponse.success(tvShowList)
            EspressoIdlingResource.decrement()
        }
        return tvShowResults
    }

    suspend fun getMovieDetail(movieId: Int): LiveData<ApiResponse<MovieEntity>> {
        EspressoIdlingResource.increment()
        val movieResult = MutableLiveData<ApiResponse<MovieEntity>>()
        RetrofitService.apiInterface.getMovieDetail(movieId).await().let { movie ->
            movieResult.value = ApiResponse.success(movie)
            EspressoIdlingResource.decrement()
        }
        return movieResult
    }

    suspend fun getTvShowDetail(tvShowId: Int): LiveData<ApiResponse<TvShowEntity>> {
        EspressoIdlingResource.increment()
        val tvShowResult = MutableLiveData<ApiResponse<TvShowEntity>>()
        RetrofitService.apiInterface.getTvShowDetail(tvShowId).await().let { tvShow ->
            tvShowResult.value = ApiResponse.success(tvShow)
            EspressoIdlingResource.decrement()
        }
        return tvShowResult
    }

//    interface LoadMoviesCallback {
//        fun onAllMoviesReceived(movieResponses: List<MovieEntity>)
//    }
//
//    interface LoadTvShowsCallback {
//        fun onAllTvShowsReceived(tvShowsResponses: List<TvShowEntity>)
//    }
//
//    interface LoadMovieDetailCallback {
//        fun onMovieDetailReceived(movieDetailResponse: MovieEntity)
//    }
//
//    interface LoadTvShowDetailCallback {
//        fun onTvShowDetailReceived(tvShowDetailResponse: TvShowEntity)
//    }
}