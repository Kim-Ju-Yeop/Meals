package com.project.meals.view.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.meals.R
import com.project.meals.databinding.ActivitySearchBinding
import com.project.meals.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    lateinit var binding : ActivitySearchBinding
    lateinit var viewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        binding = DataBindingUtil.setContentView(this@SearchActivity, R.layout.activity_search)
        viewModel = ViewModelProviders.of(this@SearchActivity).get(SearchViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner
    }

    // RecyclerView Data Add
//    fun test(){
//        var adapter = SearchAdapter()
//
//        adapter.addItems(SearchInfo("김주엽고등학교", "김주엽 마음속"))
//        binding.recyclerView.adapter = adapter
//    }
}
