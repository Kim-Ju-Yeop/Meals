package org.techtown.project5.view.meals

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import org.techtown.project5.view.meals.title_fragment.TodayFragment
import org.techtown.project5.view.meals.title_fragment.TomorrowFragment
import org.techtown.project5.view.meals.title_fragment.YesterDayFragment

class MealsAdapter(fm: FragmentManager, val pageCount : Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                val yesterday =
                    YesterDayFragment()
                return yesterday
            }
            1->{
                val today =
                    TodayFragment()
                return today
            }
            2->{
                val tomorrow =
                    TomorrowFragment()
                return tomorrow
            }
            else->{
                return null!!
            }
        }
    }

    override fun getCount(): Int {
        return pageCount
    }
}