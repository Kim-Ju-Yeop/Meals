package org.techtown.project5.viewmodel

import android.content.SharedPreferences
import org.techtown.project5.viewmodel.base.BaseViewModel


class MainViewModel : BaseViewModel() {

    fun CheckLogin(loginData : SharedPreferences){
        if(loginData.getBoolean("loginData", false)) onSuccessEvent.call()
        else onFailEvent.call()
    }
}