package com.febian.android.moviecatalog.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity

@Dao
interface CatalogDao {

    @Query("SELECT * FROM movie_entities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_entities where favorited == 1")
    fun getFavoritedMovies(): LiveData<List<MovieEntity>>

    @Update
    fun updateMovie(course: MovieEntity)

    @Query("SELECT * FROM tv_show_entities")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show_entities where favorited == 1")
    fun getFavoritedTvShows(): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movies: List<TvShowEntity>)

    @Update
    fun updateTvShow(course: TvShowEntity)

}