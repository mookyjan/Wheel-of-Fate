package com.mudassirkhan.data.remote.api

import com.mudassirkhan.data.remote.entities.EngineerListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface WheelApiService {

    @GET("engineers")
    fun getEngineerList(): Single<EngineerListResponse>
}