package com.febian.android.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(

    @field:SerializedName("id")
    var genreId: Int,

    @field:SerializedName("name")
    var name: String,
)