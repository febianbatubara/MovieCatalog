package com.febian.android.moviecatalog.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.source.CatalogRepository
import com.febian.android.moviecatalog.utils.DummyDataFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var movieObserver: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DummyDataFactory.generateDummyMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(catalogRepository.getPopularMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        Mockito.verify(catalogRepository).getPopularMovies()
        assertNotNull(movieEntities)
        assertEquals(dummyMovies.size, movieEntities?.size)

        viewModel.getMovies().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovies)
    }
}