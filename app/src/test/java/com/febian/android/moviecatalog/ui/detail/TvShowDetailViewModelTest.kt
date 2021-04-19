package com.febian.android.moviecatalog.ui.detail

import com.febian.android.moviecatalog.utils.DummyDataFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowDetailViewModelTest {

    private lateinit var viewModel: TvShowDetailViewModel
    private val dummyTvShow = DummyDataFactory.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.tvShowId

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel()
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedTvShow(dummyTvShow.tvShowId)
        val tvShowEntity = viewModel.getTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.description, tvShowEntity.description)
        assertEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTvShow.genre, tvShowEntity.genre)
        assertEquals(dummyTvShow.rating, tvShowEntity.rating)
        assertEquals(dummyTvShow.posterPath, tvShowEntity.posterPath)
        assertEquals(dummyTvShow.posterBgPath, tvShowEntity.posterBgPath)
    }
}