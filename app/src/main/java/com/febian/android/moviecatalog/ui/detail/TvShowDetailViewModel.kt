package com.febian.android.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.vo.Resource
import javax.inject.Inject

class TvShowDetailViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    val tvShowId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var tvShow: LiveData<Resource<TvShowEntity>> = Transformations.switchMap(tvShowId) {
        catalogRepository.getTvShowDetail(it)
    }

    fun setFavoriteTvShow() {
        val tvShowEntity = tvShow.value?.data
        if (tvShowEntity != null) {
            val newState = !tvShowEntity.favorited
            catalogRepository.setFavoriteTvShow(tvShowEntity, newState)
        }
    }
}