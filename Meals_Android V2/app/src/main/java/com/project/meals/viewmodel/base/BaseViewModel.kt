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
}