package com.project.meals.view.meals

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.project.meals.R
import com.project.meals.databinding.ActivityMealsBinding
import com.project.meals.view.base.BaseActivity
import com.project.meals.view.search_school.SearchSchoolActivity
import com.project.meals.viewmodel.meals.MealsViewModel
import java.text.SimpleDateFormat
import java.util.*


class MealsActivity : BaseActivity() {

    lateinit var binding : ActivityMealsBinding
    lateinit var viewModel : MealsViewModel

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
        setTime(binding.timer)
        setToolbar(binding.toolbar)
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
            override fun onTabSelected(tab: TabLayout.Tab?) = binding.viewPager.setCurrentItem(tab!!.position)
            override fun onTabUnselected(tab: TabLayout.Tab?){}
            override fun onTabReselected(tab: TabLayout.Tab?){}
        })
    }

    // Toolbar Setting
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater : MenuInflater = menuInflater
        menuInflater.inflate(R.menu.meals_item, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_school ->{
                startActivity(Intent(this@MealsActivity, SearchSchoolActivity::class.java))
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            R.id.bug_report ->{
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/rD8MAjQgVZfQ4yVv9")))
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
