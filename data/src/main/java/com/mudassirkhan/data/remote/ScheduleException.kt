package com.mudassirkhan.data.remote

class ScheduleException(message: String) : RuntimeException(message) {


    companion object {

        // General exception
        const val SCHEDULE_GENERAL_EXCEPTION = "An error occurred while generating the schedule."
        // Invalid exception
        const val SCHEDULE_INVALID_EXCEPTION =
            "Base on the given setting we can not create the schedule."
    }


}