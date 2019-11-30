package com.mudassirkhan.data.repositories

import android.util.Log
import com.mudassirkhan.data.UseCaseResult
import com.mudassirkhan.data.mapper.DataToDomainMapper
import com.mudassirkhan.data.remote.api.WheelApiService
import com.mudassirkhan.domain.entity.Engineer
import com.mudassirkhan.domain.gateway.EngineerGateway
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class EngineerListRepositoryImpl(private val engineerRepository: EngineerRepository): EngineerGateway {


    private val mapperEngineer = DataToDomainMapper()

    override fun getEngineerList(): Single<List<Engineer>> {

        return engineerRepository.getEngineerList()
            .doOnError {
                println("Engineer list error")
            }
//            .map {
//                UseCaseResult.Success(it)
//
//            }
            .map {
                it.engineers?.map {
                    mapperEngineer.mapDataToDomainEngineer(it)
                }
            }

    }


}