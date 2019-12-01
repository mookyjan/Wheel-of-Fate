package com.mudassirkhan.domain.interactor

import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.UseCase
import com.mudassirkhan.domain.entity.Engineer
import com.mudassirkhan.domain.gateway.EngineerGateway
import io.reactivex.Single

class GetEngineerListUseCase(schedulers: Schedulers,
    private val engineerGateway: EngineerGateway) : UseCase<Void, List<Engineer>>(schedulers) {

    override fun buildObservable(params: Void?): Single<List<Engineer>> {
        val result = engineerGateway.getEngineerList()
        return result
    }

}