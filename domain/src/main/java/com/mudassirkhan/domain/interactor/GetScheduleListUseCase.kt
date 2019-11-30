package com.mudassirkhan.domain.interactor

import com.mudassirkhan.domain.MissingUseCaseParameterException
import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.UseCase
import com.mudassirkhan.domain.entity.Engineer
import com.mudassirkhan.domain.entity.Schedule
import com.mudassirkhan.domain.gateway.ScheduleGateway
import io.reactivex.Single

class GetScheduleListUseCase constructor(private val schedulers: Schedulers,
                                         private val scheduleGateway: ScheduleGateway) :
    UseCase<List<Engineer>,List<Schedule>>(schedulers) {
    override fun buildObservable(params: List<Engineer>?): Single<List<Schedule>> {
        if (params == null) throw MissingUseCaseParameterException(javaClass)
        val result = scheduleGateway.getSchedule(params)
        return result
    }
}