package com.project.meals.viewmodel.base

import androidx.lifecycle.ViewModel
import com.project.meals.network.NetRetrofit
import com.project.meals.widget.SingleLiveEvent

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
}