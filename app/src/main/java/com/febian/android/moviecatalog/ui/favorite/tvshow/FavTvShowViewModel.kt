package com.febian.android.moviecatalog.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class FavTvShowViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getFavoriteTvShows(sort: String): LiveData<PagedList<TvShowEntity>> =
        catalogRepository.getFavoritedTvShows(sort)
}