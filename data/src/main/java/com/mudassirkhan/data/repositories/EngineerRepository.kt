package com.mudassirkhan.data.repositories

import com.mudassirkhan.data.UseCaseResult
import com.mudassirkhan.data.mapper.DataToDomainMapper
import com.mudassirkhan.data.remote.RemoteDataSource
import com.mudassirkhan.data.remote.entities.EngineerListResponse
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class EngineerRepository (private val remoteDataSource: RemoteDataSource,
                          private val mapper: DataToDomainMapper) {

    fun getEngineerList() : Single<EngineerListResponse>{

//        var useCaseResult = UseCaseResult<T>
     return   remoteDataSource.getEngineerList()
            .map {
                it.engineers?.map {
                    mapper.mapDataToDomainEngineer(it)
                }
                it
//                UseCaseResult.Success(it)
            }



    }

}