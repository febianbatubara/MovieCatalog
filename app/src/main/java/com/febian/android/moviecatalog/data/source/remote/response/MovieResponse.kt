package com.febian.android.moviecatalog.data.source.remote.response

import com.febian.android.moviecatalog.data.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("page")
    var page: Int,

    @field:SerializedName("results")
    var results: List<MovieEntity>,

    @field:SerializedName("total_pages")
    var total_pages: Int,

    @field:SerializedName("total_results")
    var total_results: Int
)