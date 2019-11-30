package com.mudassirkhan.data.remote

import com.mudassirkhan.data.mapper.DataToDomainMapper
import com.mudassirkhan.data.remote.api.WheelApiService

class RemoteDataSource (private val apiService: WheelApiService) {


      fun getEngineerList() = apiService.getEngineerList()

}