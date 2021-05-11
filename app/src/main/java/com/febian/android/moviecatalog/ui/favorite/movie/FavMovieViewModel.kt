package com.febian.android.moviecatalog.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import javax.inject.Inject

class FavMovieViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getFavoriteMovies(sort: String): LiveData<PagedList<MovieEntity>> =
        catalogRepository.getFavoritedMovies(sort)
}