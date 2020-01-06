package com.project.meals.view.meals.title_fragment

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.project.meals.R
import com.project.meals.databinding.FragmentYesterdayBinding
import com.project.meals.viewmodel.meals.title_viewmodel.YesterdayViewModel

class YesterDayFragment : Fragment() {

    lateinit var binding : FragmentYesterdayBinding
    lateinit var viewModel : YesterdayViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_yesterday, container, false)
        viewModel = ViewModelProviders.of(this).get(YesterdayViewModel::class.java)

        return binding.root
    }
}
