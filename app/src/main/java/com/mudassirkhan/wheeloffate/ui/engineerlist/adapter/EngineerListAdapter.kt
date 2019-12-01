package com.mudassirkhan.wheeloffate.ui.engineerlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.wheeloffate.R
import com.mudassirkhan.wheeloffate.databinding.SingleEngineerItemBinding
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer

class EngineerListAdapter (private val engineerList : List<Engineer>):RecyclerView.Adapter<EngineerListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: SingleEngineerItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.d { "engineer list $engineerList" }
        val inflater = LayoutInflater.from(parent.context)
        val binding: SingleEngineerItemBinding = DataBindingUtil.inflate(inflater, R.layout.single_engineer_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return engineerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     holder.binding.engineer = engineerList.get(position)
        holder.binding.executePendingBindings()
    }

    interface Callbacks {
        fun onItemClick(view: View, item: Engineer)
    }
}