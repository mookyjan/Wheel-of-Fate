package com.mudassirkhan.data.remote.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Engineer (
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
) : Parcelable