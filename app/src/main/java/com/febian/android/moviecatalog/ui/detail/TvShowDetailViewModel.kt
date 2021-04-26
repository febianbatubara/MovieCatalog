package com.febian.android.moviecatalog.ui.detail

import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.utils.DummyDataFactory

class TvShowDetailViewModel : ViewModel() {

    private var tvShowId: Int = 0

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): TvShowEntity {
        lateinit var tvShow: TvShowEntity
        val tvShowsEntities = DummyDataFactory.generateDummyTvShows()
        for (tvShowEntity in tvShowsEntities) {
            if (tvShowEntity.tvShowId == tvShowId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}