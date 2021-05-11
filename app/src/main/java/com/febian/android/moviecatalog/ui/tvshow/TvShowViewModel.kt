package com.febian.android.moviecatalog.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.vo.Resource
import javax.inject.Inject

class TvShowViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> =
        catalogRepository.getPopularTvShows()
}