package com.mudassirkhan.wheeloffate.ui.engineerlist

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.domain.interactor.GetEngineerListUseCase
import com.mudassirkhan.wheeloffate.mapper.mapDomainToPresentationEngineer
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class EngineerListViewModel @Inject constructor(private val engineerListUseCase: GetEngineerListUseCase
                                               ) : ViewModel() {

     var engineerList = ObservableArrayList<Engineer>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean(true)

    fun getEngineerList(){
        loading.value = true
        engineerListUseCase.execute()
            .subscribeBy(onSuccess = {
                Timber.d { "engineer list api response success $it" }
                loading.postValue(false)
                engineerList.clear()
                it.let {
                    engineerList.addAll(mapDomainToPresentationEngineer(it))
                }
                empty.set(it.isEmpty())

            },onError = {
                Timber.e { "engineer api error $it" }
                loading.value=false
                error.set(it.localizedMessage ?: it.message ?: "Unkown error")
            }).addTo(compositeDisposable)
    }
}
