package com.febian.android.moviecatalog.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.febian.android.moviecatalog.data.CatalogRepository
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.vo.Resource
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> =
        catalogRepository.getPopularMovies()
}