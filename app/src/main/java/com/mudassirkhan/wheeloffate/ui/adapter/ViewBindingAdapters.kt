package com.mudassirkhan.wheeloffate.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mudassirkhan.domain.entity.Schedule
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer
import com.mudassirkhan.wheeloffate.ui.shiftschedule.adapter.ScheduleListAdapter

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("engineerAdapter", "engineerCallbacks", requireAll = false)
    fun setProductAdapter(recyclerView: RecyclerView, items: List<Engineer>?, callbacks: EngineerListAdapter.Callbacks?) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = EngineerListAdapter(it)
        }
    }



        @JvmStatic
        @BindingAdapter("scheduleAdapter", "scheduleCallbacks", requireAll = false)
        fun setScheduleAdapter(recyclerView: RecyclerView, items: List<Schedule>?, callbacks: ScheduleListAdapter.Callbacks?) {
            items?.let {
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = ScheduleListAdapter(it)
            }
        }

}