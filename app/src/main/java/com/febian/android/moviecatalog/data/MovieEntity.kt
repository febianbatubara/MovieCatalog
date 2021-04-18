package com.febian.android.moviecatalog.data

data class MovieEntity(
    var movieId: String,
    var title: String,
    var description: String,
    var releaseDate: String,
    var genre: String,
    var rating: String,
    var posterPath: String,
    var posterBgPath: String,
    var favorited: Boolean = false
)