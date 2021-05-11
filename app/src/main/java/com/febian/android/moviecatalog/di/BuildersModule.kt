package com.febian.android.moviecatalog.di

import com.febian.android.moviecatalog.di.activity.favorite.FavoriteBuildersModule
import com.febian.android.moviecatalog.di.activity.home.HomeBuildersModule
import com.febian.android.moviecatalog.ui.detail.MovieDetailActivity
import com.febian.android.moviecatalog.ui.detail.TvShowDetailActivity
import com.febian.android.moviecatalog.ui.favorite.FavoriteActivity
import com.febian.android.moviecatalog.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [HomeBuildersModule::class])
    abstract fun injectHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [FavoriteBuildersModule::class])
    abstract fun injectFavoriteActivity(): FavoriteActivity

    @ContributesAndroidInjector
    abstract fun injectMovieDetailActivity(): MovieDetailActivity

    @ContributesAndroidInjector
    abstract fun injectTvSHowDetailActivity(): TvShowDetailActivity
}