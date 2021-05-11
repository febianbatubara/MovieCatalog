package com.febian.android.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.febian.android.moviecatalog.data.source.local.LocalDataSource
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource
import com.febian.android.moviecatalog.data.source.remote.response.MovieResponse
import com.febian.android.moviecatalog.data.source.remote.response.TvShowResponse
import com.febian.android.moviecatalog.data.source.remote.vo.ApiResponse
import com.febian.android.moviecatalog.utils.AppExecutors
import com.febian.android.moviecatalog.utils.SortUtils
import com.febian.android.moviecatalog.utils.SortUtils.MOVIE_ENTITY
import com.febian.android.moviecatalog.utils.SortUtils.TV_SHOW_ENTITY
import com.febian.android.moviecatalog.vo.Resource
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogDataSource {

    companion object {
        private val pagingConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
    }

    override fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getAllMovies(), pagingConfig).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
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
                            tagline,
                            releaseDate,
                            rating,
                            voteCount,
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

    override fun getPopularTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> =
                LivePagedListBuilder(localDataSource.getAllTvShows(), pagingConfig).build()

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
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
                            tagline,
                            releaseDate,
                            rating,
                            voteCount,
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

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object :
            NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieDetail(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null || data.tagline.isNullOrBlank()

            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieDetail(movieId)

            public override fun saveCallResult(data: MovieResponse) {
                with(data) {
                    val movie = MovieEntity(
                        movieId,
                        title,
                        description,
                        tagline,
                        releaseDate,
                        rating,
                        voteCount,
                        posterPath,
                        posterBgPath,
                        favorited
                    )
                    localDataSource.updateMovie(movie)
                }
            }
        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object :
            NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowDetail(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null || data.tagline.isNullOrBlank()

            public override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShowDetail(tvShowId)

            public override fun saveCallResult(data: TvShowResponse) {
                with(data) {
                    val tvShow = TvShowEntity(
                        tvShowId,
                        title,
                        description,
                        tagline,
                        releaseDate,
                        rating,
                        voteCount,
                        posterPath,
                        posterBgPath,
                        favorited
                    )
                    localDataSource.updateTvShow(tvShow)
                }
            }
        }.asLiveData()
    }

    override fun getFavoritedMovies(sort: String): LiveData<PagedList<MovieEntity>> {
        val query = SortUtils.getSortedQuery(sort, MOVIE_ENTITY)
        return LivePagedListBuilder(localDataSource.getFavoritedMovies(query), pagingConfig).build()
    }

    override fun getFavoritedTvShows(sort: String): LiveData<PagedList<TvShowEntity>> {
        val query = SortUtils.getSortedQuery(sort, TV_SHOW_ENTITY)
        return LivePagedListBuilder(
            localDataSource.getFavoritedTvShows(query),
            pagingConfig
        ).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }
}