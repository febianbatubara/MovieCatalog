package com.febian.android.moviecatalog.utils

object GenreConverter {

    fun getGenres(genreIds: List<Int>): String {
        val genreHashMap: HashMap<Int, String> = HashMap()
        genreHashMap[28] = "Action"
        genreHashMap[12] = "Adventure"
        genreHashMap[16] = "Animation"
        genreHashMap[35] = "Comedy"
        genreHashMap[80] = "Crime"
        genreHashMap[99] = "Documentary"
        genreHashMap[18] = "Drama"
        genreHashMap[10751] = "Family"
        genreHashMap[14] = "Fantasy"
        genreHashMap[36] = "History"
        genreHashMap[27] = "Horror"
        genreHashMap[10402] = "Music"
        genreHashMap[9648] = "Mystery"
        genreHashMap[10749] = "Romance"
        genreHashMap[878] = "Science Fiction"
        genreHashMap[10770] = "TV Movie"
        genreHashMap[53] = "Thriller"
        genreHashMap[10752] = "War"
        genreHashMap[37] = "Western"
        genreHashMap[10759] = "Action & Adventure"
        genreHashMap[10762] = "Kids"
        genreHashMap[10763] = "News"
        genreHashMap[10764] = "Reality"
        genreHashMap[10765] = "Sci-Fi & Fantasy"
        genreHashMap[10766] = "Soap"
        genreHashMap[10767] = "Talk"
        genreHashMap[10768] = "War & Politics"

        val genreList = ArrayList<String>()
        genreIds.forEach { genreId ->
            val genre = genreHashMap[genreId]
            genre?.let { it -> genreList.add(it) }
        }

        return genreList.joinToString(", ")
    }
}