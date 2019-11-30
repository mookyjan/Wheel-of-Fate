package com.mudassirkhan.wheeloffate.ui.shiftschedule

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mudassirkhan.data.Constants

import com.mudassirkhan.wheeloffate.R
import com.mudassirkhan.wheeloffate.databinding.ShiftScheduleFragmentBinding
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ShiftScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = ShiftScheduleFragment()
    }


    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ShiftScheduleViewModel
    private lateinit var mBinding : ShiftScheduleFragmentBinding
   private lateinit var engineerList: ArrayList<Engineer>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel= ViewModelProviders.of(this, this.viewModeFactory).get(ShiftScheduleViewModel::class.java)

        if (arguments != null)
            engineerList = arguments?.getParcelableArrayList<Engineer>(Constants.ENGINEER_LIST)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
          mBinding = DataBindingUtil.inflate(inflater,R.layout.shift_schedule_fragment, container, false)
         mBinding.viewModel =viewModel

        viewModel.getSchedule(engineerList)

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(ShiftScheduleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
