package com.mudassirkhan.wheeloffate.di.module

import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.gateway.EngineerGateway
import com.mudassirkhan.domain.gateway.ScheduleGateway
import com.mudassirkhan.domain.interactor.GetEngineerListUseCase
import com.mudassirkhan.domain.interactor.GetScheduleListUseCase
import com.mudassirkhan.wheeloffate.di.module.scheduler.AppSchedulers
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideEngineerListUseCase(schedulers: Schedulers,engineerGateway: EngineerGateway): GetEngineerListUseCase{
        return  GetEngineerListUseCase(schedulers,engineerGateway)
    }

    @Provides
    fun provideScheduleListUseCase(schedulers: Schedulers,scheduleGateway: ScheduleGateway) : GetScheduleListUseCase{

        return GetScheduleListUseCase(schedulers,scheduleGateway)
    }
}