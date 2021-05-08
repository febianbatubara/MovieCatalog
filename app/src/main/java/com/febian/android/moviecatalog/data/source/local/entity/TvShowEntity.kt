package com.febian.android.moviecatalog.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.febian.android.moviecatalog.data.source.remote.response.GenreResponse

@Entity(tableName = "tv_show_entities")
data class TvShowEntity(

    @PrimaryKey(autoGenerate = false)
    var tvShowId: Int,
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