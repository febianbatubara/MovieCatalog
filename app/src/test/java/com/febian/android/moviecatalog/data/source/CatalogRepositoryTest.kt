package com.febian.android.moviecatalog.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.febian.android.moviecatalog.data.FakeCatalogRepository
import com.febian.android.moviecatalog.data.source.remote.RemoteDataSource
import com.febian.android.moviecatalog.utils.DummyDataFactory
import com.febian.android.moviecatalog.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CatalogRepositoryTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote)

    private val movieResponses = DummyDataFactory.generateDummyMovies()
    private val movieResponse = DummyDataFactory.generateDummyMovies()[0]
    private val movieId = movieResponse.movieId
    private val tvShowResponses = DummyDataFactory.generateDummyTvShows()
    private val tvShowResponse = DummyDataFactory.generateDummyTvShows()[0]
    private val tvShowId = tvShowResponse.tvShowId

    @Test
    fun getPopularMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                    movieResponses
                )
                null
            }.`when`(remote).getPopularMovies(any())
        }
        val movieEntities = LiveDataTestUtil.getValue(catalogRepository.getPopularMovies())
        runBlocking { verify(remote).getPopularMovies(any()) }
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getPopularTvShows() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onAllTvShowsReceived(
                    tvShowResponses
                )
                null
            }.`when`(remote).getPopularTvShows(any())
        }
        val tvShowEntities = LiveDataTestUtil.getValue(catalogRepository.getPopularTvShows())
        runBlocking { verify(remote).getPopularTvShows(any()) }
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(
                    movieResponse
                )
                null
            }.`when`(remote).getMovieDetail(eq(movieId), any())
        }
        val movieEntity = LiveDataTestUtil.getValue(catalogRepository.getMovieDetail(movieId))
        runBlocking { verify(remote).getMovieDetail(eq(movieId), any()) }
        assertNotNull(movieEntity)
        assertEquals(movieResponse.movieId, movieEntity.movieId)
    }

    @Test
    fun getTvShowDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(
                    tvShowResponse
                )
                null
            }.`when`(remote).getTvShowDetail(eq(tvShowId), any())
        }
        val tvShowEntity = LiveDataTestUtil.getValue(catalogRepository.getTvShowDetail(tvShowId))
        runBlocking { verify(remote).getTvShowDetail(eq(tvShowId), any()) }
        assertNotNull(tvShowEntity)
        assertEquals(tvShowResponse.tvShowId, tvShowEntity.tvShowId)
    }
}