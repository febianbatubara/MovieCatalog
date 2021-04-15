package com.febian.android.moviecatalog.data

data class MovieEntity(
    var movieId: String,
    var title: String,
    var description: String,
    var releaseDate: String,
    var imagePath: String,
    var rating: String,
    var favorited: Boolean = false
)