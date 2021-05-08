package com.febian.android.moviecatalog.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_entities")
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    var movieId: Int,

    @field:SerializedName("title")
    var title: String,

    @field:SerializedName("overview")
    var description: String,

    @field:SerializedName("release_date")
    var releaseDate: String,

    @field:SerializedName("genre_ids")
    var genreIds: List<Int>? = listOf(),

    @field:SerializedName("genres")
    var genres: List<GenreEntity>? = listOf(),

    @field:SerializedName("vote_average")
    var rating: Float,

    @field:SerializedName("poster_path")
    var posterPath: String,

    @field:SerializedName("backdrop_path")
    var posterBgPath: String,

    var favorited: Boolean = false
)