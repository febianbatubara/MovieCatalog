package com.febian.android.moviecatalog.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.febian.android.moviecatalog.data.FakeCatalogRepository
import com.febian.android.moviecatalog.data.source.local.LocalDataSource
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource
import com.febian.android.moviecatalog.utils.*
import com.febian.android.moviecatalog.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val testExecutors: AppExecutors = AppExecutors(TestExecutor(), TestExecutor())
    private val catalogRepository = FakeCatalogRepository(remote, local, testExecutors)

    private val movieResponses = DummyDataFactory.generateDummyMovies()
    private val movieResponse = DummyDataFactory.generateDummyMovies()[0]
    private val movieId = movieResponse.movieId
    private val tvShowResponses = DummyDataFactory.generateDummyTvShows()
    private val tvShowResponse = DummyDataFactory.generateDummyTvShows()[0]
    private val tvShowId = tvShowResponse.tvShowId

    private val movieDataSource =
        mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
    private val tvShowDataSource =
        mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>

    @Test
    fun getPopularMovies() {
        Mockito.`when`(local.getAllMovies()).thenReturn(movieDataSource)
        catalogRepository.getPopularMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyDataFactory.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        movieEntities.data?.size?.let { assertEquals(movieResponses.size.toLong(), it.toLong()) }
    }

    @Test
    fun getFavoriteMovies() {
        val sortType = SortUtils.TITLE_ASC
        val query = SortUtils.getSortedQuery(sortType, SortUtils.MOVIE_ENTITY)
        Mockito.`when`(local.getFavoritedMovies(query)).thenReturn(movieDataSource)

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyDataFactory.generateDummyMovies()))
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movieResponse
        Mockito.`when`(local.getMovieDetail(movieId)).thenReturn(dummyMovie)

        val liveDataValue = LiveDataTestUtil.getValue(catalogRepository.getMovieDetail(movieId))
        Mockito.verify(local).getMovieDetail(movieId)
        assertNotNull(liveDataValue)
        assertEquals(movieId, liveDataValue.data?.movieId)
    }

    @Test
    fun setFavoriteMovie() {
        doNothing().`when`(local).setMovieFavorite(movieResponse, true)
        catalogRepository.setFavoriteMovie(movieResponse, true)

        verify(local, times(1)).setMovieFavorite(movieResponse, true)
    }

    @Test
    fun getPopularTvShows() {
        Mockito.`when`(local.getAllTvShows()).thenReturn(tvShowDataSource)
        catalogRepository.getPopularTvShows()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyDataFactory.generateDummyTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities.data)
        tvShowEntities.data?.size?.let { assertEquals(tvShowResponses.size.toLong(), it.toLong()) }
    }

    @Test
    fun getFavoriteTvShows() {
        val sortType = SortUtils.TITLE_ASC
        val query = SortUtils.getSortedQuery(sortType, SortUtils.TV_SHOW_ENTITY)
        Mockito.`when`(local.getFavoritedTvShows(query)).thenReturn(tvShowDataSource)

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyDataFactory.generateDummyTvShows()))
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = tvShowResponse
        Mockito.`when`(local.getTvShowDetail(tvShowId)).thenReturn(dummyTvShow)

        val liveDataValue = LiveDataTestUtil.getValue(catalogRepository.getTvShowDetail(tvShowId))
        Mockito.verify(local).getTvShowDetail(tvShowId)
        assertNotNull(liveDataValue)
        assertEquals(tvShowId, liveDataValue.data?.tvShowId)
    }

    @Test
    fun setFavoriteTvShow() {
        doNothing().`when`(local).setTvShowFavorite(tvShowResponse, true)
        catalogRepository.setFavoriteTvShow(tvShowResponse, true)

        verify(local, times(1)).setTvShowFavorite(tvShowResponse, true)
    }
}