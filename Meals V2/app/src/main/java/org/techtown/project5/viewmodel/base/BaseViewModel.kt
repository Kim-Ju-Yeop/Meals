package org.techtown.project5.viewmodel.base

import androidx.lifecycle.ViewModel
import org.techtown.project5.network.NetRetrofit
import org.techtown.project5.widget.SingleLiveEvent
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

open class BaseViewModel : ViewModel(){

    // NetRetrofit
    val netRetrofit = NetRetrofit()

    // SingleLiveEvent
    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    // MealsList
    val mealsDataList = ArrayList<String>()

    var breakfastList = ArrayList<String>()
    var lunchList = ArrayList<String>()
    var dinnerList = ArrayList<String>()

    val onBreakfastEvent = SingleLiveEvent<Unit>()
    val onLunchEvent = SingleLiveEvent<Unit>()
    val onDinnerEvent = SingleLiveEvent<Unit>()

    var checkCount : Int = 0

    val date = Date()
    val simpleDateFormat = SimpleDateFormat("HH")
}