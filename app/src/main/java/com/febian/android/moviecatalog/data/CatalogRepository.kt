package com.febian.android.moviecatalog.data

import androidx.lifecycle.LiveData
import com.febian.android.moviecatalog.data.source.local.LocalDataSource
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.source.remote.ApiResponse
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource
import com.febian.android.moviecatalog.data.source.remote.response.MovieResponse
import com.febian.android.moviecatalog.data.source.remote.response.TvShowResponse
import com.febian.android.moviecatalog.utils.AppExecutors
import com.febian.android.moviecatalog.vo.Resource

class CatalogRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    CatalogDataSource {

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(
                    remoteData,
                    localData,
                    appExecutors
                ).apply { instance = this }
            }
    }

    override fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getPopularMovies()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                data.forEach { responseData ->
                    with(responseData) {
                        val movie = MovieEntity(
                            movieId,
                            title,
                            description,
                            releaseDate,
                            rating,
                            posterPath,
                            posterBgPath,
                            favorited
                        )
                        movieList.add(movie)
                    }
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object :
            NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getPopularTvShows()

            public override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                data.forEach { responseData ->
                    with(responseData) {
                        val tvShow = TvShowEntity(
                            tvShowId,
                            title,
                            description,
                            releaseDate,
                            rating,
                            posterPath,
                            posterBgPath,
                            favorited
                        )
                        tvShowList.add(tvShow)
                    }
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        localDataSource.getMovieDetail(movieId)

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
        localDataSource.getTvShowDetail(tvShowId)

    override fun getFavoritedMovies(): LiveData<List<MovieEntity>> =
        localDataSource.getFavoritedMovies()

    override fun getFavoritedTvShows(): LiveData<List<TvShowEntity>> =
        localDataSource.getFavoritedTvShows()

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }
}