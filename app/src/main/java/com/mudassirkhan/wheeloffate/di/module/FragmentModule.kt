package com.mudassirkhan.wheeloffate.di.module

import com.mudassirkhan.wheeloffate.ui.engineerlist.EngineerListFragment
import com.mudassirkhan.wheeloffate.ui.shiftschedule.ShiftScheduleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun engineerListFragment(): EngineerListFragment

    @ContributesAndroidInjector
    internal abstract fun shiftScheduleFragment() : ShiftScheduleFragment

}