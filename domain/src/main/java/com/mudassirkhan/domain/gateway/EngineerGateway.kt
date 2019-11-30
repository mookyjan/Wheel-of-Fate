package com.mudassirkhan.domain.gateway

import com.mudassirkhan.domain.entity.Engineer
import io.reactivex.Single

interface EngineerGateway {

    fun getEngineerList(): Single<List<Engineer>>
}