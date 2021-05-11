package com.febian.android.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.utils.DummyDataFactory
import com.febian.android.moviecatalog.vo.Resource
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
import org.mockito.Mockito.`when`
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
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(catalogRepository)
    }

    @get:Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Test
    fun getTvShow() {
        val dummyTvShow = Resource.success(DummyDataFactory.generateDummyTvShows()[0])
        viewModel.setSelectedTvShow(tvShowId)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(catalogRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        viewModel.tvShow = catalogRepository.getTvShowDetail(tvShowId)
        val tvShowEntity = viewModel.tvShow.value?.data as TvShowEntity
        verify(catalogRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.data?.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTvShow.data?.title, tvShowEntity.title)
        assertEquals(dummyTvShow.data?.description, tvShowEntity.description)
        assertEquals(dummyTvShow.data?.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTvShow.data?.rating, tvShowEntity.rating)
        assertEquals(dummyTvShow.data?.posterPath, tvShowEntity.posterPath)
        assertEquals(dummyTvShow.data?.posterBgPath, tvShowEntity.posterBgPath)

        viewModel.tvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }

    @Test
    fun getWrongTvShowItem() {
        val dummyWrongTvShow = Resource.success(DummyDataFactory.generateDummyTvShows()[1])
        viewModel.setSelectedTvShow(tvShowId)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyWrongTvShow

        `when`(catalogRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        viewModel.tvShow = catalogRepository.getTvShowDetail(tvShowId)
        val tvShowEntity = viewModel.tvShow.value?.data as TvShowEntity
        verify(catalogRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntity)
        Assert.assertNotEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        Assert.assertNotEquals(dummyTvShow.title, tvShowEntity.title)
        Assert.assertNotEquals(dummyTvShow.description, tvShowEntity.description)
        Assert.assertNotEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)

        viewModel.tvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyWrongTvShow)
    }

    @Test
    fun getEmptyTvShow() {
        thrown.expect(java.lang.NullPointerException::class.java)

        viewModel.setSelectedTvShow(tvShowId)
        val tvShowEntity = viewModel.tvShow.value?.data as TvShowEntity
        Assert.assertNull(tvShowEntity)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DummyDataFactory.generateDummyTvShows()[0])
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(catalogRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        viewModel.tvShow = catalogRepository.getTvShowDetail(tvShowId)
        viewModel.setFavoriteTvShow()
        verify(catalogRepository).setFavoriteTvShow(tvShow.value?.data as TvShowEntity, true)
    }
}