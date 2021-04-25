package com.febian.android.moviecatalog.ui.detail

import com.febian.android.moviecatalog.utils.DummyDataFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel
    private val dummyMovie = DummyDataFactory.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @get:Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.description, movieEntity.description)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.rating, movieEntity.rating)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.posterBgPath, movieEntity.posterBgPath)
    }

    @Test
    fun getEmptyMovie() {
        thrown.expect(java.lang.IndexOutOfBoundsException::class.java)
        thrown.expectMessage("Index: 10, Size: 10")

        val falseDummyMovie = DummyDataFactory.generateDummyMovies()[10]
        viewModel.setSelectedMovie(falseDummyMovie.movieId)
        val movieEntity = viewModel.getMovie()
        assertNull(movieEntity)
    }
}