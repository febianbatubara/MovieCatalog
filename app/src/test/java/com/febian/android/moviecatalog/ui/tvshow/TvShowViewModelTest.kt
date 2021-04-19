package com.febian.android.moviecatalog.ui.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getMovies() {
        val tvShowEntities = viewModel.getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}