package com.mudassirkhan.wheeloffate.ui.engineerlist

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.domain.interactor.GetEngineerListUseCase
import com.mudassirkhan.wheeloffate.R
import com.mudassirkhan.wheeloffate.mapper.mapDomainToPresentationEngineer
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer
import com.mudassirkhan.wheeloffate.util.IResourceProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.engineer_list_fragment.view.*
import javax.inject.Inject


//TODO we can also use baseView Model for common items like compoisteDisposable ,loading etc but for simplicity
//it is kept here

class EngineerListViewModel @Inject constructor(private val engineerListUseCase: GetEngineerListUseCase,
                                                private val iResourceProvider: IResourceProvider
                                               ) : ViewModel() {

    //variable for the engineer list
    var engineerList = ObservableArrayList<Engineer>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    //variable for loading progress
    val loading = MutableLiveData<Boolean>()
    //variable for error message
    val error = ObservableField<String>()
    val empty = ObservableBoolean(true)

    /**
     * fun to get the list of engineers from api
     */
    fun getEngineerList(){
        loading.value = true
        engineerListUseCase.execute()
            .subscribeBy(onSuccess = {
                Timber.d { "engineer list api response success $it" }
                loading.postValue(false)
                engineerList.clear()
                it.let {
                    //map engineer class from domain to presentation engineer
                    engineerList.addAll(mapDomainToPresentationEngineer(it))
                }
                empty.set(it.isEmpty())

            },onError = {
                //TODO error handling can be improved much more by knowing the proper error response.
                Timber.e { "engineer api error $it" }
                loading.value=false
                error.set(it.localizedMessage ?: it.message ?: iResourceProvider.context.getString(R.string.txt_error))
            }).addTo(compositeDisposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
