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
            try {
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
                                    genres,
                                    rating,
                                    posterPath,
                                    posterBgPath,
                                    favorited
                                )
                                movieList.add(movie)
                            }
                        }
                        movieResults.postValue(movieList)
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return movieResults
    }

    override fun getPopularTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        CoroutineScope(IO).launch {
            try {
                remoteDataSource.getPopularTvShows(object : RemoteDataSource.LoadTvShowsCallback {
                    override fun onAllTvShowsReceived(tvShowsResponses: List<TvShowEntity>) {
                        val tvShowList = ArrayList<TvShowEntity>()
                        tvShowsResponses.forEach { response ->
                            with(response) {
                                val tvShow = TvShowEntity(
                                    tvShowId,
                                    title,
                                    description,
                                    releaseDate,
                                    genreIds,
                                    genres,
                                    rating,
                                    posterPath,
                                    posterBgPath,
                                    favorited
                                )
                                tvShowList.add(tvShow)
                            }
                        }
                        tvShowResults.postValue(tvShowList)
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return tvShowResults
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        CoroutineScope(IO).launch {
            try {
                remoteDataSource.getMovieDetail(
                    movieId,
                    object : RemoteDataSource.LoadMovieDetailCallback {
                        override fun onMovieDetailReceived(movieDetailResponse: MovieEntity) {
                            with(movieDetailResponse) {
                                val movie = MovieEntity(
                                    movieId,
                                    title,
                                    description,
                                    releaseDate,
                                    genreIds,
                                    genres,
                                    rating,
                                    posterPath,
                                    posterBgPath,
                                    favorited
                                )
                                movieResult.postValue(movie)
                            }
                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return movieResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        CoroutineScope(IO).launch {
            try {
                remoteDataSource.getTvShowDetail(
                    tvShowId,
                    object : RemoteDataSource.LoadTvShowDetailCallback {
                        override fun onTvShowDetailReceived(tvShowDetailResponse: TvShowEntity) {
                            with(tvShowDetailResponse) {
                                val tvShow = TvShowEntity(
                                    tvShowId,
                                    title,
                                    description,
                                    releaseDate,
                                    genreIds,
                                    genres,
                                    rating,
                                    posterPath,
                                    posterBgPath,
                                    favorited
                                )
                                tvShowResult.postValue(tvShow)
                            }
                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return tvShowResult
    }
}