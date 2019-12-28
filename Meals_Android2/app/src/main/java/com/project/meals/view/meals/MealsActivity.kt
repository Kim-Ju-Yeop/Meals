package com.project.meals.view.meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.project.meals.R
import com.project.meals.databinding.ActivityMealsBinding
import com.project.meals.viewmodel.MealsViewModel

class MealsActivity : AppCompatActivity() {

    lateinit var binding : ActivityMealsBinding
    lateinit var viewModel : MealsViewModel

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

        val adapter = Title_Adapter(supportFragmentManager, binding.layoutTab.tabCount)
        binding.viewPager.adapter = adapter

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.layoutTab))

        binding.layoutTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.setCurrentItem(tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
