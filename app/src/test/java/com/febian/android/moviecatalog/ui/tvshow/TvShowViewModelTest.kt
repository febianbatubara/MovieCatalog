package com.febian.android.moviecatalog.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.CatalogRepository
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var tvShowObserver: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = DummyDataFactory.generateDummyTvShows()
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        Mockito.`when`(catalogRepository.getPopularTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value
        Mockito.verify(catalogRepository).getPopularTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(dummyTvShows.size, tvShowEntities?.size)

        viewModel.getTvShows().observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(dummyTvShows)
    }
}