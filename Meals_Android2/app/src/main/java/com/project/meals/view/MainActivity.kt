package com.project.meals.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.meals.R
import com.project.meals.databinding.ActivityMainBinding
import com.project.meals.view.intro.AppIntroActivity
import com.project.meals.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SharedPreferences
        var checkLogin : SharedPreferences = getSharedPreferences("checkLogin", Context.MODE_PRIVATE)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        observerViewModel()
        viewModel.CheckLogin(checkLogin)
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@MainActivity, Observer {
                // 급식 표시 화면
            })

            onFailEvent.observe(this@MainActivity, Observer {
                startActivity(Intent(this@MainActivity, AppIntroActivity::class.java))
            })
        }
    }
}
