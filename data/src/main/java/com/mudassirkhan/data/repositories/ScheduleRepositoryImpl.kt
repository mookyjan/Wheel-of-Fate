package com.mudassirkhan.data.repositories

import com.mudassirkhan.data.remote.ScheduleService
import com.mudassirkhan.domain.entity.Engineer
import com.mudassirkhan.domain.entity.Schedule
import com.mudassirkhan.domain.gateway.ScheduleGateway
import io.reactivex.Single
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ScheduleRepositoryImpl (): ScheduleGateway{

    override fun getSchedule(engineerList: List<Engineer>): Single<List<Schedule>> {
        val schedulService = ScheduleService(ArrayList(engineerList))
        return  Single.create {emitter ->
            try {
                emitter.onSuccess(schedulService.schedules!!)
            }catch (e :Exception ){
                emitter.onError(e)
            }
        }
    }
}