package com.febian.android.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.CatalogRepository

class MovieDetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    private var movieId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovie(): LiveData<MovieEntity> = catalogRepository.getMovieDetail(movieId)
}