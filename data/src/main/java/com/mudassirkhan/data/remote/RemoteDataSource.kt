package com.mudassirkhan.data.remote

import com.mudassirkhan.data.remote.api.WheelApiService
import com.mudassirkhan.data.remote.entities.EngineerListResponse
import io.reactivex.Single

class RemoteDataSource(private val apiService: WheelApiService) {


    fun getEngineerList(): Single<EngineerListResponse> = apiService.getEngineerList()

}