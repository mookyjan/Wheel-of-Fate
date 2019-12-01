package com.mudassirkhan.wheeloffate.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mudassirkhan.wheeloffate.ui.engineerlist.EngineerListViewModel
import com.mudassirkhan.wheeloffate.ui.shiftschedule.ShiftScheduleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(EngineerListViewModel::class)
    internal abstract fun provideEngineerListViewModel(viewModel: EngineerListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShiftScheduleViewModel::class)
    internal abstract fun  provideShiftScheduleViewModel(viewModel: ShiftScheduleViewModel) : ViewModel
}