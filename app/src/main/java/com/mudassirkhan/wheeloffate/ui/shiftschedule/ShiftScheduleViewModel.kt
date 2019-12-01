package com.mudassirkhan.wheeloffate.ui.shiftschedule

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber

import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.entity.Schedule

import com.mudassirkhan.domain.interactor.GetScheduleListUseCase
import com.mudassirkhan.wheeloffate.mapper.mapPresentationToDomainEngineer
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ShiftScheduleViewModel @Inject constructor(private val scheduleListUseCase: GetScheduleListUseCase) : ViewModel() {


    //variable for schedule list
    var scheduleList = ObservableArrayList<Schedule>()
    val compositeDisposable = CompositeDisposable()

    /**
     * fun to get the schedule
     */
    fun getSchedule(engineerList : List<Engineer>){
        scheduleListUseCase.execute(mapPresentationToDomainEngineer(engineerList))
            .subscribeBy(onSuccess = {
                Timber.d { "schedule list success $it" }
                scheduleList.addAll(it)
            },onError = {
                Timber.e { "schedule error $it" }
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
