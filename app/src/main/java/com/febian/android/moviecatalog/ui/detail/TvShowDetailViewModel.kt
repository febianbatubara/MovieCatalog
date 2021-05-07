package com.febian.android.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.CatalogRepository

class TvShowDetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    private var tvShowId: Int = 0

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): LiveData<TvShowEntity> = catalogRepository.getTvShowDetail(tvShowId)
}