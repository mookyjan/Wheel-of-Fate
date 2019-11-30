package com.mudassirkhan.wheeloffate.di.module

import com.mudassirkhan.data.mapper.DataToDomainMapper
import com.mudassirkhan.data.remote.RemoteDataSource
import com.mudassirkhan.data.remote.api.WheelApiService
import com.mudassirkhan.data.repositories.EngineerListRepositoryImpl
import com.mudassirkhan.data.repositories.EngineerRepository
import com.mudassirkhan.data.repositories.ScheduleRepositoryImpl
import com.mudassirkhan.domain.gateway.EngineerGateway
import com.mudassirkhan.domain.gateway.ScheduleGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {


    @Provides
    @Singleton
    internal fun provideRemoteDataSource(wheelApiService: WheelApiService):RemoteDataSource{
        return RemoteDataSource(wheelApiService)
    }

    @Provides
    @Singleton
    internal fun provideEngineerRepository(remoteDataSource: RemoteDataSource):EngineerRepository{
        return EngineerRepository(remoteDataSource, DataToDomainMapper())
    }

    @Provides
    @Singleton
    internal fun provideEngineerGateway(engineerRepository: EngineerRepository): EngineerGateway{
        return EngineerListRepositoryImpl(engineerRepository)
    }

    @Provides
    @Singleton
    internal fun provideScheduleGateWay(): ScheduleGateway{
        return ScheduleRepositoryImpl()
    }
}