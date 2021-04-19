package com.febian.android.moviecatalog.ui.detail

import com.febian.android.moviecatalog.utils.DummyDataFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel
    private val dummyMovie = DummyDataFactory.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel()
        viewModel.setSelectedMovie(movieId)
    }

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
}