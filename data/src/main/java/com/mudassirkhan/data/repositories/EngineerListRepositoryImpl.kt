package com.mudassirkhan.data.repositories

import com.mudassirkhan.data.mapper.DataToDomainMapper
import com.mudassirkhan.domain.entity.Engineer
import com.mudassirkhan.domain.gateway.EngineerGateway
import io.reactivex.Single

class EngineerListRepositoryImpl(private val engineerRepository: EngineerRepository): EngineerGateway {


    private val mapperEngineer = DataToDomainMapper()

    override fun getEngineerList(): Single<List<Engineer>> {

        return engineerRepository.getEngineerList()
            .doOnError {
                println("Engineer list error")
            }.map {
                println("engineer list $it")
                mapperEngineer.mapLocalToDomainEngineer(it)
            }
//            .map {
//                it.engineers?.map {
//                    mapperEngineer.mapDataToDomainEngineer(it)
//                }
//            }

    }


}