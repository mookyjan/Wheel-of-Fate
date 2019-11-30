package com.mudassirkhan.domain.gateway

import com.mudassirkhan.domain.entity.Engineer
import com.mudassirkhan.domain.entity.Schedule
import io.reactivex.Single

interface ScheduleGateway {

    fun getSchedule(engineerList: List<Engineer>): Single<List<Schedule>>
}