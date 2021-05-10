package com.febian.android.moviecatalog.data.source.local.entity

import androidx.room.*

@Entity(tableName = "tv_show_entities")
data class TvShowEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String = "",

    @ColumnInfo(name = "rating")
    var rating: Float = 0f,

    @ColumnInfo(name = "posterPath")
    var posterPath: String = "",

    @ColumnInfo(name = "posterBgPath")
    var posterBgPath: String = "",

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)