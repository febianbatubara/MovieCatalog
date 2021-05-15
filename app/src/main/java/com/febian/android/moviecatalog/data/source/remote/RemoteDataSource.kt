package com.febian.android.moviecatalog.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.febian.android.moviecatalog.data.source.remote.api.ApiInterface
import com.febian.android.moviecatalog.data.source.remote.response.MovieResponse
import com.febian.android.moviecatalog.data.source.remote.response.TvShowResponse
import com.febian.android.moviecatalog.data.source.remote.vo.ApiResponse
import com.febian.android.moviecatalog.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) {

    fun getPopularMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(IO).launch {
            try {
                apiInterface.getPopularMovies().await().results.let { movieList ->
                    movieResults.postValue(ApiResponse.success(movieList))
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
                apiInterface.getPopularTvShows().await().results.let { tvShowList ->
                    tvShowResults.postValue(ApiResponse.success(tvShowList))
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
                apiInterface.getMovieDetail(movieId).await().let { movie ->
                    movieResult.postValue(ApiResponse.success(movie))
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
                apiInterface.getTvShowDetail(tvShowId).await().let { tvShow ->
                    tvShowResult.postValue(ApiResponse.success(tvShow))
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return tvShowResult
    }
}