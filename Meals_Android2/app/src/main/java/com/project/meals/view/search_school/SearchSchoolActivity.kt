package com.project.meals.view.search_school

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.meals.R
import com.project.meals.databinding.ActivitySearchBinding
import com.project.meals.viewmodel.SearchViewModel

class SearchSchoolActivity : AppCompatActivity() {

    lateinit var binding : ActivitySearchBinding
    lateinit var viewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        binding = DataBindingUtil.setContentView(this@SearchSchoolActivity, R.layout.activity_search)
        viewModel = ViewModelProviders.of(this@SearchSchoolActivity).get(SearchViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        observerViewModel()
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@SearchSchoolActivity, Observer {
                val adapter = SearchSchoolAdapter(viewModel.schoolDataList)
                binding.recyclerView.adapter = adapter
            })
        }
    }
}
