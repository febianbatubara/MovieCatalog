package com.febian.android.moviecatalog.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity

class FavMovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = catalogRepository.getFavoritedMovies()

    fun setFavorite(movie: MovieEntity) {
        val newState = !movie.favorited
        catalogRepository.setFavoriteMovie(movie, newState)
    }
}