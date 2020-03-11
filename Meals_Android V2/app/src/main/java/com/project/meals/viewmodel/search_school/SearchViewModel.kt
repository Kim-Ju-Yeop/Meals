package com.project.meals.viewmodel.search_school

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.meals.DTO.SearchSchool
import com.project.meals.network.Data
import com.project.meals.network.Response
import com.project.meals.viewmodel.base.BaseViewModel
import com.project.meals.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SearchViewModel : BaseViewModel() {

    val schoolName = MutableLiveData<String>()

    var schoolServerData = ArrayList<SearchSchool>()
    var schoolDataList = ArrayList<SearchSchool>()

    val onNoEvent = SingleLiveEvent<Unit>()

    fun searchSchool() = onSearch(schoolName.value.toString())

    fun onSearch(schoolName : String){
        val res : Call<Response<Data>> = netRetrofit.school.searchSchool(schoolName)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                when(response.code()){
                    200 -> {
                        schoolDataList.clear()
                        schoolServerData = response.body()?.data?.schoolList as ArrayList<SearchSchool>

                        if(schoolServerData != null){
                            for(A in 0 until schoolServerData.size){
                                schoolDataList.add(SearchSchool(schoolServerData.get(A).school_name, schoolServerData.get(A).school_locate, schoolServerData.get(A).office_code, schoolServerData.get(A).school_code, schoolServerData.get(A).school_type))
                            }
                            onSuccessEvent.call()
                        }
                    }
                    400 -> onNoEvent.call()
                    404 -> onFailEvent.call()
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("onSearchSchool[Error]", "학교 검색 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }
}