package com.mudassirkhan.wheeloffate.di.module

import android.content.Context
import com.mudassirkhan.data.local.EngineerLocalDataSource
import com.mudassirkhan.data.local.database.WheelFateDatabase
import com.mudassirkhan.data.local.dao.EngineerDao
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
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {


    @Provides
    @Singleton
    internal fun provideRemoteDataSource(wheelApiService: WheelApiService):RemoteDataSource{
        return RemoteDataSource(wheelApiService)
    }


//    @Provides
//    @Singleton
//    internal fun provideDatabase(@Named("application.context") context: Context):WheelFateDatabase{
//        return WheelFateDatabase.newInstance(context)
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideEngineerDao(wheelOfFateDatabase: WheelFateDatabase):EngineerDao{
//        return wheelOfFateDatabase.engineerDao()
//    }

    @Provides
    @Singleton
    internal fun provideLocalDataSource(engineerDao: EngineerDao):EngineerLocalDataSource{
        return EngineerLocalDataSource(engineerDao)
    }

    @Provides
    @Singleton
    internal fun provideEngineerRepository(remoteDataSource: RemoteDataSource,localEngineerLocalDataSource: EngineerLocalDataSource):EngineerRepository{
        return EngineerRepository(remoteDataSource,localEngineerLocalDataSource ,DataToDomainMapper())
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


    @Provides
    @Singleton
    internal fun provideWheelOfFateDatabaseDatabase(context: Context): WheelFateDatabase {

        return WheelFateDatabase.newInstance(context)
    }

    @Provides
    @Singleton
    internal fun provideWheelOfFateDao(wheelOfFateDatabase: WheelFateDatabase): EngineerDao = wheelOfFateDatabase.engineerDao()

}