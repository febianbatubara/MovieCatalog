package com.febian.android.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource

class CatalogRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CatalogDataSource {

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(remoteData: RemoteDataSource): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteData).apply { instance = this }
            }
    }

    override fun getPopularMovies(): LiveData<List<MovieEntity>> {
        var movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: MutableLiveData<List<MovieEntity>>) {
                movieResults = movieResponses
            }
        })
        return movieResults
    }

    override fun getPopularTvShows(): LiveData<List<TvShowEntity>> {
        var tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getPopularTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowsResponses: MutableLiveData<List<TvShowEntity>>) {
                tvShowResults = tvShowsResponses
            }
        })
        return tvShowResults
    }
}