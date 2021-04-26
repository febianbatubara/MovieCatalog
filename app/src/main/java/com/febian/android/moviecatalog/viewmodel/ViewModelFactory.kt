package com.febian.android.moviecatalog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.febian.android.moviecatalog.data.source.CatalogRepository
import com.febian.android.moviecatalog.di.Injection
import com.febian.android.moviecatalog.ui.movie.MovieViewModel
import com.febian.android.moviecatalog.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mCatalogRepository: CatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mCatalogRepository) as T
            }
//            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
//                MovieDetailViewModel(mCatalogRepository) as T
//            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }

}