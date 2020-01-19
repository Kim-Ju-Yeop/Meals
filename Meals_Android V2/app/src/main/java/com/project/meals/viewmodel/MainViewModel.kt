package com.project.meals.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.project.meals.widget.SingleLiveEvent

class MainViewModel : ViewModel() {

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    fun CheckLogin(loginData : SharedPreferences){
        if(loginData.getBoolean("loginData", false)){
            onSuccessEvent.call()
        } else{
            onFailEvent.call()
        }
    }
}