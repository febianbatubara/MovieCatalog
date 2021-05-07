package com.febian.android.moviecatalog.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.CatalogRepository

class MovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = catalogRepository.getPopularMovies()
}