package com.project.meals.view.meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.project.meals.R
import com.project.meals.databinding.ActivityMealsBinding
import com.project.meals.view.meals.title_fragment.TodayFragment
import com.project.meals.view.meals.title_fragment.TomorrowFragment
import com.project.meals.view.meals.title_fragment.YesterDayFragment
import com.project.meals.viewmodel.MealsViewModel

class MealsActivity : AppCompatActivity() {

    lateinit var binding : ActivityMealsBinding
    lateinit var viewModel : MealsViewModel

    val yesterDayFragment = YesterDayFragment()
    val todayFragment = TodayFragment()
    val tomorrowFragment = TomorrowFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        binding = DataBindingUtil.setContentView(this@MealsActivity, R.layout.activity_meals)
        viewModel = ViewModelProviders.of(this@MealsActivity).get(MealsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        setTab()
    }

    fun setTab(){
        binding.layoutTab.addTab(binding.layoutTab.newTab().setText("어제"))
        binding.layoutTab.addTab(binding.layoutTab.newTab().setText("오늘"))
        binding.layoutTab.addTab(binding.layoutTab.newTab().setText("내일"))

        binding.layoutTab.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position

                when(position){
                    0-> supportFragmentManager.beginTransaction().replace(R.id.container, yesterDayFragment).commit()
                    1-> supportFragmentManager.beginTransaction().replace(R.id.container, todayFragment).commit()
                    2-> supportFragmentManager.beginTransaction().replace(R.id.container, tomorrowFragment).commit()
                }
            }
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }
        })
    }
}
