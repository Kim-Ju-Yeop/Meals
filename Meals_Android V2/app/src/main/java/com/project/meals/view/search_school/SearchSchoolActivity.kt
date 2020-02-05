package com.project.meals.view.search_school

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.meals.R
import com.project.meals.databinding.ActivitySearchBinding
import com.project.meals.view.base.BaseActivity
import com.project.meals.viewmodel.search_school.SearchViewModel

class SearchSchoolActivity : BaseActivity() {

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
        addData()
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@SearchSchoolActivity, Observer {

                binding.recyclerView.visibility = View.VISIBLE
                binding.questionLayout.visibility = View.GONE

                val adapter = SearchSchoolAdapter(this@SearchSchoolActivity, viewModel.schoolDataList)
                binding.recyclerView.adapter = adapter
            })
            onNoEvent.observe(this@SearchSchoolActivity, Observer {
                binding.recyclerView.visibility = View.GONE
                binding.questionLayout.visibility = View.VISIBLE

                binding.answerTextView.text = "아직 검색된 학교 이름이 없습니다."
            })
            onFailEvent.observe(this@SearchSchoolActivity, Observer {
                binding.recyclerView.visibility = View.GONE
                binding.questionLayout.visibility = View.VISIBLE

                binding.answerTextView.text = "입력하신 학교는 존재하지 않습니다."
            })
        }
    }

    // Keyboard Enter
    fun addData(){
        binding.searchSchoolEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.searchSchool()
            }
            false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
