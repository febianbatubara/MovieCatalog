package com.febian.android.moviecatalog.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val TITLE_ASC = "Title Asc"
    const val TITLE_DESC = "Title Desc"
    const val BEST_RATING = "Best Rating"
    const val RANDOM = "Random"
    const val MOVIE_ENTITY = "movie_entities"
    const val TV_SHOW_ENTITY = "tv_show_entities"

    fun getSortedQuery(filter: String, tableName: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $tableName WHERE favorited == 1 ")
        when (filter) {
            TITLE_DESC -> simpleQuery.append("ORDER BY title DESC")
            TITLE_ASC -> simpleQuery.append("ORDER BY title ASC")
            BEST_RATING -> simpleQuery.append("ORDER BY rating DESC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}