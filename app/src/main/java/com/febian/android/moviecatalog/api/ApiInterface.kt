package com.febian.android.moviecatalog.api

import com.febian.android.moviecatalog.BuildConfig
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.data.source.remote.response.Response
import com.febian.android.moviecatalog.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("discover/movie")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Constant.language
    ): Call<Response<MovieEntity>>

    @GET("discover/tv")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Constant.language
    ): Call<Response<TvShowEntity>>

    @GET("movie/{movieId}")
    fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Constant.language
    ): Call<MovieEntity>

    @GET("tv/{tvShowId}")
    fun getTvShowDetail(
        @Path("tvShowId") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Constant.language
    ): Call<TvShowEntity>
}