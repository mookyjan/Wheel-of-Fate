package com.mudassirkhan.data.repositories

import com.mudassirkhan.data.local.EngineerLocalDataSource
import com.mudassirkhan.data.local.model.EngineerLocalModel
import com.mudassirkhan.data.mapper.DataToDomainMapper
import com.mudassirkhan.data.remote.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single

class EngineerRepository (private val remoteDataSource: RemoteDataSource,
                          private val engieerLocalDataSource : EngineerLocalDataSource,
                          private val mapper: DataToDomainMapper) {

    fun getEngineerList() : Single<List<EngineerLocalModel>>{

        val local = engieerLocalDataSource.getEngineersList()
            .filter { !it.isEmpty() }
        val remote = remoteDataSource.getEngineerList()
            .toObservable()
            .map { mapper.toLocal(it.engineers!!) }
            .doOnNext {
                print("engineer local $it")
                engieerLocalDataSource.insertAll(it)
            }

        return Observable.concat(local.toObservable(),remote)
            .firstElement()
            .toSingle()

    }

    }

//}