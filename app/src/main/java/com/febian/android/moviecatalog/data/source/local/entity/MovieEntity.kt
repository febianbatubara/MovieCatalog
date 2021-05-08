package com.febian.android.moviecatalog.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.febian.android.moviecatalog.data.source.remote.response.GenreResponse

@Entity(tableName = "movie_entities")
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    var movieId: Int,
    var title: String,
    var description: String,
    var releaseDate: String,
    var genreIds: List<Int>? = listOf(),
    var genres: List<GenreResponse>? = listOf(),
    var rating: Float,
    var posterPath: String,
    var posterBgPath: String,
    var favorited: Boolean = false
)