package com.febian.android.moviecatalog.ui.detail

import androidx.lifecycle.ViewModel
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.utils.DummyDataFactory

class MovieDetailViewModel : ViewModel() {

    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val moviesEntities = DummyDataFactory.generateDummyMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.movieId == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }
}