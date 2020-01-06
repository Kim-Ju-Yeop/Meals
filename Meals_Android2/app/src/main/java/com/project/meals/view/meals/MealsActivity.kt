package com.project.meals.view.meals

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.project.meals.R
import com.project.meals.databinding.ActivityMealsBinding
import com.project.meals.viewmodel.meals.MealsViewModel
import java.text.SimpleDateFormat
import java.util.*

class MealsActivity : AppCompatActivity() {

    lateinit var binding : ActivityMealsBinding
    lateinit var viewModel : MealsViewModel

    lateinit var timer : CountDownTimer

    lateinit var school_name : String
    lateinit var school_id : String
    lateinit var office_id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        binding = DataBindingUtil.setContentView(this@MealsActivity, R.layout.activity_meals)
        viewModel = ViewModelProviders.of(this@MealsActivity).get(MealsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        checkData()
        setTab()
        setTime()
    }

    fun checkData(){
        val sharedPreferences = getSharedPreferences("checkSearch", Context.MODE_PRIVATE)

        school_name = sharedPreferences.getString("school_name", "").toString()
        school_id = sharedPreferences.getString("school_id", "").toString()
        office_id = sharedPreferences.getString("office_id", "").toString()

        binding.schoolName.text = school_name

        // MARQUEE 효과
        binding.schoolName.setSingleLine(true)
        binding.schoolName.ellipsize = TextUtils.TruncateAt.MARQUEE
        binding.schoolName.isSelected = true
    }

    // Tab Setting
    fun setTab(){
        binding.layoutTab.addTab(binding.layoutTab.newTab().setText("어제"))
        binding.layoutTab.addTab(binding.layoutTab.newTab().setText("오늘"))
        binding.layoutTab.addTab(binding.layoutTab.newTab().setText("내일"))

        val mealsAdapter = MealsAdapter(supportFragmentManager, binding.layoutTab.tabCount)
        binding.viewPager.adapter = mealsAdapter

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.layoutTab))

        binding.layoutTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.setCurrentItem(tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    // Timer Setting
    fun setTime(){
        timer = object : CountDownTimer((10*1000), 1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text = getTime()
            }
            override fun onFinish() {
                timer.cancel()
                timer.start()
            }
        }
        timer.start()
    }
    fun getTime() : String{
        var mFormat = SimpleDateFormat("kk : mm : ss a", Locale.KOREA)
        val mNow = System.currentTimeMillis()
        val mDate = Date(mNow)

        return mFormat.format(mDate)
    }
}
