package com.febian.android.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.utils.DummyDataFactory
import com.febian.android.moviecatalog.vo.Resource
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
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(catalogRepository)
    }

    @get:Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Test
    fun getMovie() {
        val dummyMovie = Resource.success(DummyDataFactory.generateDummyMovies()[0])
        viewModel.setSelectedMovie(movieId)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movie)
        viewModel.movie = catalogRepository.getMovieDetail(movieId)
        val movieEntity = viewModel.movie.value?.data as MovieEntity
        verify(catalogRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.data?.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.data?.title, movieEntity.title)
        assertEquals(dummyMovie.data?.description, movieEntity.description)
        assertEquals(dummyMovie.data?.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.data?.rating, movieEntity.rating)
        assertEquals(dummyMovie.data?.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.data?.posterBgPath, movieEntity.posterBgPath)

        viewModel.movie.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getWrongMovieItem() {
        val dummyWrongMovie = Resource.success(DummyDataFactory.generateDummyMovies()[1])
        viewModel.setSelectedMovie(movieId)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyWrongMovie

        `when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movie)
        viewModel.movie = catalogRepository.getMovieDetail(movieId)
        val movieEntity = viewModel.movie.value?.data as MovieEntity
        verify(catalogRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertNotEquals(dummyMovie.movieId, movieEntity.movieId)
        assertNotEquals(dummyMovie.title, movieEntity.title)
        assertNotEquals(dummyMovie.description, movieEntity.description)
        assertNotEquals(dummyMovie.releaseDate, movieEntity.releaseDate)

        viewModel.movie.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyWrongMovie)
    }

    @Test
    fun getEmptyMovie() {
        thrown.expect(java.lang.NullPointerException::class.java)

        viewModel.setSelectedMovie(movieId)
        val movieEntity = viewModel.movie.value?.data as MovieEntity
        assertNull(movieEntity)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DummyDataFactory.generateDummyMovies()[0])
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movie)
        viewModel.movie = catalogRepository.getMovieDetail(movieId)
        viewModel.setFavoriteMovie()
        verify(catalogRepository).setFavoriteMovie(movie.value?.data as MovieEntity, true)
    }
}