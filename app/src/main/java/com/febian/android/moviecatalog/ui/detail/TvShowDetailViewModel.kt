package com.febian.android.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity

class TvShowDetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    val tvShowId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var tvShow: LiveData<TvShowEntity> = Transformations.switchMap(tvShowId) {
        catalogRepository.getTvShowDetail(it)
    }

    fun setFavoriteTvShow() {
        val tvShowEntity = tvShow.value
        if (tvShowEntity != null) {
            val newState = !tvShowEntity.favorited
            catalogRepository.setFavoriteTvShow(tvShowEntity, newState)
        }
    }
}