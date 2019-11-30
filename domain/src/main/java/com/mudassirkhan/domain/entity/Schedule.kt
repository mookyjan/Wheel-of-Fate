package com.mudassirkhan.domain.entity

data class Schedule(val scheduleId: String, val day: Int, val shiftEngineers: List<Engineer>)