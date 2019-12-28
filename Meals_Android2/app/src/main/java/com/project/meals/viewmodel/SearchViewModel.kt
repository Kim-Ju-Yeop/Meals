package com.project.meals.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.annotations.Until
import com.project.meals.model.SearchSchool
import com.project.meals.network.Data
import com.project.meals.network.NetRetrofit
import com.project.meals.network.response.Response
import com.project.meals.view.search_school.SearchSchoolAdapter
import com.project.meals.view.search_school.SearchSchoolInfo
import com.project.meals.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SearchViewModel : ViewModel() {

    val neRetrofit = NetRetrofit()
    val schoolName = MutableLiveData<String>()

    var schoolServerData = ArrayList<SearchSchool>()
    var schoolDataList = ArrayList<SearchSchool>()

    val onSuccessEvent = SingleLiveEvent<Unit>()

    fun searchSchool(){
        onSearch(schoolName.value.toString())
    }

    fun onSearch(schoolName : String){
        val res : Call<Response<Data>> = neRetrofit.school.searchSchool(schoolName)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("Server", "서버 통신 X")
            }
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {

                if(response.code() == 200){
                    schoolDataList.clear()
                    schoolServerData = response.body()?.data?.schoolList as ArrayList<SearchSchool>

                    if(schoolServerData != null){
                        for(A in 0 until schoolServerData.size){
                            schoolDataList.add(SearchSchool(schoolServerData.get(A).school_name, schoolServerData.get(A).school_locate, schoolServerData.get(A).office_code, schoolServerData.get(A).school_code, schoolServerData.get(A).school_type))
                        }
                        onSuccessEvent.call()
                    }
                }
            }
        })
    }
}