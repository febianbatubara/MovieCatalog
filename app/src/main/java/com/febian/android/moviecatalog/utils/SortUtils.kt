package com.febian.android.moviecatalog.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val TITLE_ASC = "Title Asc"
    const val TITLE_DESC = "Title Desc"
    const val BEST_RATING = "Best Rating"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie_entities WHERE favorited == 1 ")
        when (filter) {
            TITLE_DESC -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            TITLE_ASC -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            BEST_RATING -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}