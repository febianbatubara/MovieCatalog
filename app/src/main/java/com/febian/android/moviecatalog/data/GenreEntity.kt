package com.febian.android.moviecatalog.data

import com.google.gson.annotations.SerializedName

data class GenreEntity(

    @field:SerializedName("id")
    var genreId: Int,

    @field:SerializedName("name")
    var name: String,
)