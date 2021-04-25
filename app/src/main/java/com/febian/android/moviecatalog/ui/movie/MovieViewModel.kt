package com.febian.android.moviecatalog.ui.movie

import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.utils.DummyDataFactory

class MovieViewModel : ViewModel() {

    fun getMovies(): List<MovieEntity> = DummyDataFactory.generateDummyMovies()
}