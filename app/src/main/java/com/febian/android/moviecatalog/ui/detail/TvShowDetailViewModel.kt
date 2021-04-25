package com.febian.android.moviecatalog.ui.detail

import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.utils.DummyDataFactory

class TvShowDetailViewModel : ViewModel() {

    private lateinit var tvShowId: String

    fun setSelectedTvShow(tvShowId: String) {
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