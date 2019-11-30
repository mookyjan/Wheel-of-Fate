package com.mudassirkhan.wheeloffate.ui.engineerlist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mudassirkhan.wheeloffate.MainActivity

import com.mudassirkhan.wheeloffate.R
import com.mudassirkhan.wheeloffate.core.Router
import com.mudassirkhan.wheeloffate.databinding.EngineerListFragmentBinding
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class EngineerListFragment : Fragment() {

    companion object {
        fun newInstance() =
            EngineerListFragment()
    }

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    private lateinit var viewModel: EngineerListViewModel
   private lateinit var mBinding : EngineerListFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel=   ViewModelProviders.of(this, this.viewModeFactory).get(EngineerListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.engineer_list_fragment, container, false)
        mBinding.viewModel = viewModel


        mBinding.btnGenerateSchedule.setOnClickListener {
            val args = Bundle()
            args.putParcelableArrayList(Router.ENGINEER_LIST, viewModel.engineerList as ArrayList<Engineer>)
            findNavController().navigate(R.id.action_engineerListFragment_to_scheduleFragment, args)
        }

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(EngineerListViewModel::class.java)
        viewModel.getEngineerList()
        observeEvents()
    }

    fun observeEvents(){
        viewModel.loading.observe(this, Observer {
            (activity as MainActivity).showProgress(it)
        })
    }

}
