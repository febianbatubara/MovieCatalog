package com.febian.android.moviecatalog.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.data.CatalogRepository

class TvShowViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvShowEntity>> = catalogRepository.getPopularTvShows()
}