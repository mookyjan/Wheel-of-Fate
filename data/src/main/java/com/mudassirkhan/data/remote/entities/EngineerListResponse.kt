package com.mudassirkhan.data.remote.entities

import com.squareup.moshi.Json

data class EngineerListResponse (
    @Json(name = "engineers")
    var engineers: List<Engineer>? = null
)