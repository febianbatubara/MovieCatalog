package com.febian.android.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

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
        val movieResults = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadMoviesCallback {
                override fun onAllMoviesReceived(movieResponses: List<MovieEntity>) {
                    val movieList = ArrayList<MovieEntity>()
                    movieResponses.forEach { response ->
                        with(response) {
                            val movie = MovieEntity(
                                movieId,
                                title,
                                description,
                                releaseDate,
                                genreIds,
                                rating,
                                posterPath,
                                posterBgPath,
                                false
                            )
                            movieList.add(movie)
                        }
                    }
                    movieResults.postValue(movieList)
//                    for (response in movieResponses) {
//                        val movie = MovieEntity(
//                            response.movieId,
//                            response.title,
//                            response.description,
//                            response.releaseDate,
//                            response.genreIds,
//                            response.rating,
//                            response.posterPath,
//                            response.posterBgPath,
//                            false
//                        )
//                        movieList.add(movie)
//                    }
//                    movieResults.postValue(movieList)
                }
            })
        }
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