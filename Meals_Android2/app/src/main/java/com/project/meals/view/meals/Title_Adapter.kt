package com.project.meals.view.meals

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.project.meals.view.meals.title_fragment.TodayFragment
import com.project.meals.view.meals.title_fragment.TomorrowFragment
import com.project.meals.view.meals.title_fragment.YesterDayFragment

class Title_Adapter(fm: FragmentManager, private val mPageCount: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {

            0 -> {
                return YesterDayFragment()
            }

            1 -> {
                return TodayFragment()
            }

            2 -> {
                return TomorrowFragment()
            }

            else -> return null
        }
    }

    override fun getCount(): Int {
        return mPageCount
    }
}
