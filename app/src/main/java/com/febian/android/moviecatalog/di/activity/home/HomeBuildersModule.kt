package com.febian.android.moviecatalog.di.activity.home

import com.febian.android.moviecatalog.ui.movie.MovieFragment
import com.febian.android.moviecatalog.ui.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment() : TvShowFragment
}