package com.febian.android.moviecatalog.ui.tvshow

import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.utils.DummyDataFactory

class TvShowViewModel : ViewModel() {

    fun getTvShows(): List<TvShowEntity> = DummyDataFactory.generateDummyTvShows()
}