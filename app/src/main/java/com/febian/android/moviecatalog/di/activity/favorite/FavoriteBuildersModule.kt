package com.febian.android.moviecatalog.di.activity.favorite

import com.febian.android.moviecatalog.ui.favorite.movie.FavMovieFragment
import com.febian.android.moviecatalog.ui.favorite.tvshow.FavTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFavMovieFragment(): FavMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavTvShowFragment(): FavTvShowFragment
}