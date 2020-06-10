package org.techtown.project5.view.base

import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

open class BaseFragment : Fragment(){

    // Date Setting
    val mCalendar = Calendar.getInstance()
    lateinit var today : Date
    var simpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
}