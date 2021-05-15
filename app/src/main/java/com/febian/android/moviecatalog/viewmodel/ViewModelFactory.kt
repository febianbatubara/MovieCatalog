package com.febian.android.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.ui.detail.MovieDetailViewModel
import com.febian.android.moviecatalog.ui.detail.TvShowDetailViewModel
import com.febian.android.moviecatalog.ui.favorite.movie.FavMovieViewModel
import com.febian.android.moviecatalog.ui.favorite.tvshow.FavTvShowViewModel
import com.febian.android.moviecatalog.ui.movie.MovieViewModel
import com.febian.android.moviecatalog.ui.tvshow.TvShowViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mCatalogRepository: CatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                MovieDetailViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> {
                TvShowDetailViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(FavMovieViewModel::class.java) -> {
                FavMovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(FavTvShowViewModel::class.java) -> {
                FavTvShowViewModel(mCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}