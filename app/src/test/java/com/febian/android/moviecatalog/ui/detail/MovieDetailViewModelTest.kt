package com.febian.android.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.source.CatalogRepository
import com.febian.android.moviecatalog.utils.DummyDataFactory
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel
    private val dummyMovie = DummyDataFactory.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(catalogRepository)
    }

    @get:Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(movieId)
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(catalogRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.description, movieEntity.description)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.rating, movieEntity.rating)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.posterBgPath, movieEntity.posterBgPath)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getWrongMovieItem() {
        val dummyWrongMovie = DummyDataFactory.generateDummyMovies()[1]
        viewModel.setSelectedMovie(movieId)
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyWrongMovie

        `when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(catalogRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertNotEquals(dummyMovie.movieId, movieEntity.movieId)
        assertNotEquals(dummyMovie.title, movieEntity.title)
        assertNotEquals(dummyMovie.description, movieEntity.description)
        assertNotEquals(dummyMovie.releaseDate, movieEntity.releaseDate)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyWrongMovie)
    }

    @Test
    fun getEmptyMovie() {
        thrown.expect(java.lang.NullPointerException::class.java)

        viewModel.setSelectedMovie(movieId)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        assertNull(movieEntity)
    }
}