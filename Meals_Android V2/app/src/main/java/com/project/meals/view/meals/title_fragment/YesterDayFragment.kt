package com.project.meals.view.meals.title_fragment

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.meals.R
import com.project.meals.databinding.FragmentYesterdayBinding
import com.project.meals.viewmodel.meals.title_viewmodel.YesterdayViewModel

class YesterDayFragment : Fragment() {

    lateinit var binding : FragmentYesterdayBinding
    lateinit var viewModel : YesterdayViewModel

    lateinit var school_id : String
    lateinit var office_id : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_yesterday, container, false)
        viewModel = ViewModelProviders.of(this@YesterDayFragment).get(YesterdayViewModel::class.java)


        getData()
        viewModel.getMeals(school_id, office_id)
        observerViewModel()

        return binding.root
    }

    fun getData(){
        val sharedPreferences = context!!.getSharedPreferences("checkSearch", Context.MODE_PRIVATE)

        school_id = sharedPreferences.getString("school_id", "").toString()
        office_id = sharedPreferences.getString("office_id", "").toString()
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@YesterDayFragment, Observer {
//                val adapter = TitleMealsAdapter(viewModel.lunchList)
//                binding.recyclerView.adapter = adapter
                Log.e("Success", "급식 정보를 모두 다운받았습니다.")
            })
        }
    }
}
