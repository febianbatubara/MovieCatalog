package com.febian.android.moviecatalog.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.data.source.remote.response.MovieResponse
import com.febian.android.moviecatalog.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCall {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

    fun getPopularMoviesApiCall(): MutableLiveData<List<MovieEntity>> {
        val movieList = MutableLiveData<List<MovieEntity>>()
        val call = RetrofitService.apiInterface.getPopularMovies()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return movieList
    }

//    fun getPopularMoviesApiCall(): MutableLiveData<List<MovieEntity>> {
//        val movieList = MutableLiveData<List<MovieEntity>>()
//        val call = RetrofitService.apiInterface.getPopularMovies()
//        call.enqueue(object : Callback<List<MovieEntity>> {
//            override fun onResponse(
//                call: Call<List<MovieEntity>>,
//                response: Response<List<MovieEntity>>
//            ) {
//                if (response.isSuccessful) {
//                    movieList.postValue(response.body())
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<MovieEntity>>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//        return movieList
//    }

    fun getPopularTvShowsApiCall(): MutableLiveData<List<TvShowEntity>> {
        val movieList = MutableLiveData<List<TvShowEntity>>()
        val call = RetrofitService.apiInterface.getPopularTvShows()
        call.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return movieList
    }
}