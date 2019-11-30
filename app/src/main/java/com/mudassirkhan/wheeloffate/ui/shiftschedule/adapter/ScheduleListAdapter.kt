package com.mudassirkhan.wheeloffate.ui.shiftschedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.domain.entity.Schedule
import com.mudassirkhan.wheeloffate.R
import com.mudassirkhan.wheeloffate.databinding.SingleItemScheduleBinding
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer

class ScheduleListAdapter (private val engineerList : List<Schedule>): RecyclerView.Adapter<ScheduleListAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.d { "engineer list $engineerList" }
        val inflater = LayoutInflater.from(parent.context)
        val binding: SingleItemScheduleBinding =
            DataBindingUtil.inflate(inflater, R.layout.single_item_schedule, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return engineerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.schedule = engineerList.get(position)
        holder.binding.executePendingBindings()
    }

    interface Callbacks {
        fun onItemClick(view: View, item: Engineer)
    }


    inner class ViewHolder(val binding: SingleItemScheduleBinding) :
        RecyclerView.ViewHolder(binding.root)
}