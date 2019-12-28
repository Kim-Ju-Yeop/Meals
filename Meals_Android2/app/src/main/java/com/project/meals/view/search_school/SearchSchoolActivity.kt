package com.project.meals.view.search_school

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
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
        addData()
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@SearchSchoolActivity, Observer {

                binding.recyclerView.visibility = View.VISIBLE
                binding.questionLayout.visibility = View.GONE

                val adapter = SearchSchoolAdapter(applicationContext, viewModel.schoolDataList)
                binding.recyclerView.adapter = adapter

                val sharedPreferences : SharedPreferences = getSharedPreferences("checkLogin", Context.MODE_PRIVATE)
                val editor : SharedPreferences.Editor = sharedPreferences.edit()

                editor.putBoolean("loginData", true)
                editor.commit()
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
}
