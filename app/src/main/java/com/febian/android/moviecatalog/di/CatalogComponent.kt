package com.febian.android.moviecatalog.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CatalogModule::class, NetworkModule::class, BuildersModule::class, AndroidSupportInjectionModule::class]
)
interface CatalogComponent : AndroidInjector<CatalogApplication> {

    @Component.Builder
    interface ComponentBuilder {
        @BindsInstance
        fun app(application: Application): ComponentBuilder

        fun build(): CatalogComponent
    }
}