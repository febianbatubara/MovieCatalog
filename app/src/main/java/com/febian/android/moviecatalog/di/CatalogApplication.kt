package com.febian.android.moviecatalog.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CatalogApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCatalogComponent.builder().app(this).build()
    }
}