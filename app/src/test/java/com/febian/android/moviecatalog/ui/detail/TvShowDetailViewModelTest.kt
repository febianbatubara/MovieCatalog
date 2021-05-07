package com.febian.android.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.utils.DummyDataFactory
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailViewModelTest {

    private lateinit var viewModel: TvShowDetailViewModel
    private val dummyTvShow = DummyDataFactory.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(catalogRepository)
    }

    @get:Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Test
    fun getTvShow() {
        viewModel.setSelectedTvShow(tvShowId)
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        Mockito.`when`(catalogRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow().value as TvShowEntity
        verify(catalogRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.description, tvShowEntity.description)
        assertEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTvShow.rating, tvShowEntity.rating)
        assertEquals(dummyTvShow.posterPath, tvShowEntity.posterPath)
        assertEquals(dummyTvShow.posterBgPath, tvShowEntity.posterBgPath)

        viewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }

    @Test
    fun getWrongMovieItem() {
        val dummyWrongTvShow = DummyDataFactory.generateDummyTvShows()[1]
        viewModel.setSelectedTvShow(tvShowId)
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyWrongTvShow

        Mockito.`when`(catalogRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow().value as TvShowEntity
        verify(catalogRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntity)
        Assert.assertNotEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        Assert.assertNotEquals(dummyTvShow.title, tvShowEntity.title)
        Assert.assertNotEquals(dummyTvShow.description, tvShowEntity.description)
        Assert.assertNotEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)

        viewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyWrongTvShow)
    }

    @Test
    fun getEmptyTvShow() {
        thrown.expect(java.lang.NullPointerException::class.java)

        val tvShowEntity = viewModel.getTvShow().value as TvShowEntity
        Assert.assertNull(tvShowEntity)
    }
}