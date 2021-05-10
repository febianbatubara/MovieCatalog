package com.febian.android.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity

class MovieDetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var movie: LiveData<MovieEntity> = Transformations.switchMap(movieId) {
        catalogRepository.getMovieDetail(it)
    }

    fun setFavoriteMovie() {
        val movieEntity = movie.value
        if (movieEntity != null) {
            val newState = !movieEntity.favorited
            catalogRepository.setFavoriteMovie(movieEntity, newState)
        }
    }
}