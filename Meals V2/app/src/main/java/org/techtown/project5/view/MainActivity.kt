package org.techtown.project5.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.techtown.project5.R
import org.techtown.project5.databinding.ActivityMainBinding
import org.techtown.project5.view.base.BaseActivity
import org.techtown.project5.view.intro.AppIntroActivity
import org.techtown.project5.view.meals.MealsActivity
import org.techtown.project5.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

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
                startActivity(Intent(this@MainActivity, MealsActivity::class.java))
                finish()
            })
            onFailEvent.observe(this@MainActivity, Observer {
                startActivity(Intent(this@MainActivity, AppIntroActivity::class.java))
                finish()
            })
        }
    }
}
