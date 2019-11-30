package com.mudassirkhan.wheeloffate

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.wheeloffate.di.component.AppComponent
import com.mudassirkhan.wheeloffate.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class WheelOfFateApp :Application(),HasAndroidInjector {

    @Inject
    lateinit var activityDispatcher: DispatchingAndroidInjector<Any>
    lateinit var component : AppComponent

    override fun androidInjector(): AndroidInjector<Any> {
        return  activityDispatcher
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
//        ProcessLifecycleOwner.get().lifecycle.addObserver(this);
       component = DaggerAppComponent
            .builder()
            .application(this)
            .context(this)
            .build()
       component.inject(this)
    }
}