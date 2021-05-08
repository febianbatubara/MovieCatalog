package com.febian.android.moviecatalog.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.febian.android.moviecatalog.data.source.remote.api.RetrofitService
import com.febian.android.moviecatalog.data.source.remote.response.MovieResponse
import com.febian.android.moviecatalog.data.source.remote.response.TvShowResponse
import com.febian.android.moviecatalog.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getPopularMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(IO).launch {
            try {
                RetrofitService.apiInterface.getPopularMovies().await().results.let { movieList ->
                    movieResults.value = ApiResponse.success(movieList)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return movieResults
    }

    fun getPopularTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val tvShowResults = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        CoroutineScope(IO).launch {
            try {
                RetrofitService.apiInterface.getPopularTvShows().await().results.let { tvShowList ->
                    tvShowResults.value = ApiResponse.success(tvShowList)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return tvShowResults
    }

    fun getMovieDetail(movieId: Int): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val movieResult = MutableLiveData<ApiResponse<MovieResponse>>()
        CoroutineScope(IO).launch {
            try {
                RetrofitService.apiInterface.getMovieDetail(movieId).await().let { movie ->
                    movieResult.value = ApiResponse.success(movie)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return movieResult
    }

    fun getTvShowDetail(tvShowId: Int): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        val tvShowResult = MutableLiveData<ApiResponse<TvShowResponse>>()
        CoroutineScope(IO).launch {
            try {
                RetrofitService.apiInterface.getTvShowDetail(tvShowId).await().let { tvShow ->
                    tvShowResult.value = ApiResponse.success(tvShow)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return tvShowResult
    }
}