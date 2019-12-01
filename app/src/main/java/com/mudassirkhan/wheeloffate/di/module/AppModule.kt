package com.mudassirkhan.wheeloffate.di.module

import android.app.Application
import android.content.Context
import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.wheeloffate.di.module.scheduler.AppSchedulers
import com.mudassirkhan.wheeloffate.util.IResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    @Named("application.context")
    internal fun providesContext(application: Application): Context {
        return   application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideSchedulers(): Schedulers = AppSchedulers()

    @Provides
    @Singleton
    internal fun provideResource(context: Context) = IResourceProvider(context)
//
//    @Provides
//    @Singleton
//    internal fun providePreference(context: Context) = IPreference(context)


}