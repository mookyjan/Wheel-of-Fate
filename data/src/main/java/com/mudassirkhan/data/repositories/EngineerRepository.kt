package com.mudassirkhan.data.repositories

import com.mudassirkhan.data.mapper.DataToDomainMapper
import com.mudassirkhan.data.remote.RemoteDataSource
import com.mudassirkhan.data.remote.entities.EngineerListResponse
import io.reactivex.Single

class EngineerRepository (private val remoteDataSource: RemoteDataSource,
                          private val mapper: DataToDomainMapper) {

    fun getEngineerList() : Single<EngineerListResponse>{

     return   remoteDataSource.getEngineerList()

    }

}