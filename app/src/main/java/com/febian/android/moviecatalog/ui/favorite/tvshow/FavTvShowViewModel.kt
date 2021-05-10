package com.febian.android.moviecatalog.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity

class FavTvShowViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>> = catalogRepository.getFavoritedTvShows()

    fun setFavorite(tvShow: TvShowEntity) {
        val newState = !tvShow.favorited
        catalogRepository.setFavoriteTvShow(tvShow, newState)
    }
}