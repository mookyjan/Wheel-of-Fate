package com.mudassirkhan.wheeloffate.ui.engineerlist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Engineer(var id: Int,
                    var name: String) : Parcelable