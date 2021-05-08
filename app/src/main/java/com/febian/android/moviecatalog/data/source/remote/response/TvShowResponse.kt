package com.febian.android.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("id")
    var tvShowId: Int,

    @field:SerializedName("name")
    var title: String,

    @field:SerializedName("overview")
    var description: String,

    @field:SerializedName("first_air_date")
    var releaseDate: String,

    @field:SerializedName("genre_ids")
    var genreIds: List<Int>? = listOf(),

    @field:SerializedName("genres")
    var genres: List<GenreResponse>? = listOf(),

    @field:SerializedName("vote_average")
    var rating: Float,

    @field:SerializedName("poster_path")
    var posterPath: String,

    @field:SerializedName("backdrop_path")
    var posterBgPath: String,

    var favorited: Boolean = false
)