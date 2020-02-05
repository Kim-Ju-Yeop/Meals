package com.project.meals.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.project.meals.viewmodel.base.BaseViewModel
import com.project.meals.widget.SingleLiveEvent

class MainViewModel : BaseViewModel() {

    fun CheckLogin(loginData : SharedPreferences){
        if(loginData.getBoolean("loginData", false)) onSuccessEvent.call()
        else onFailEvent.call()
    }
}