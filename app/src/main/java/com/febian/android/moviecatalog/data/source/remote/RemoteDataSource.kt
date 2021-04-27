package com.febian.android.moviecatalog.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.febian.android.moviecatalog.api.ApiCall
import com.febian.android.moviecatalog.api.ApiInterface
import com.febian.android.moviecatalog.api.RetrofitService
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource private constructor(private val apiCall: ApiCall) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        private const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiCall: ApiCall): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiCall).apply { instance = this }
            }
    }

    suspend fun getPopularMovies(callback: LoadMoviesCallback) {
//        EspressoIdlingResource.increment()
//        callback.onAllMoviesReceived(apiCall.getPopularMoviesApiCall())
//        EspressoIdlingResource.decrement()

        EspressoIdlingResource.increment()
        RetrofitService.apiInterface.getPopularMovies().await().results.let { movieList ->
            callback.onAllMoviesReceived(
                movieList
            )
            EspressoIdlingResource.decrement()
        }

    }

    fun getPopularTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllTvShowsReceived(apiCall.getPopularTvShowsApiCall())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }


//    fun getPopularTvShows(callback: LoadTvShowsCallback) {
//        EspressoIdlingResource.increment()
//        handler.postDelayed(
//            {
//                callback.onAllTvShowsReceived(jsonHelper.loadCourses())
//                EspressoIdlingResource.decrement()
//            },
//            SERVICE_LATENCY_IN_MILLIS
//        )
//    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieEntity>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowsResponses: MutableLiveData<List<TvShowEntity>>)
    }

//    interface LoadTvShowsCallback {
//        fun onAllTvShowsReceived(tvShowsResponses: List<TvShowResponse>)
//    }
}