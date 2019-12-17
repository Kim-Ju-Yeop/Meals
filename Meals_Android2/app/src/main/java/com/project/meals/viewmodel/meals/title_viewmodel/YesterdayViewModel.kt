package com.project.meals.viewmodel.meals.title_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.project.meals.network.Data
import com.project.meals.network.NetRetrofit
import com.project.meals.network.response.Response
import com.project.meals.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import java.lang.NullPointerException

class YesterdayViewModel : ViewModel() {

    val neRetrofit = NetRetrofit()
    val mealsDataList = ArrayList<String>()

    var breakfastList = ArrayList<String>()
    var lunchList = ArrayList<String>()
    var dinnerList = ArrayList<String>()

    val onSuccessEvent = SingleLiveEvent<Unit>()

    fun getMeals(school_id : String, office_id : String){
        val res : Call<Response<Data>> = neRetrofit.meals.getYesterday(school_id, office_id)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("test", "실패")
            }
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {

                if(response.code() == 200){
                    if(response.body()?.data?.meal != null){
                        Log.e("Status[200]", "어제 급식 조회를 성공하였습니다.")

                        for(A in 0 until response?.body()?.data?.meal?.size!!){
                            if(response?.body()?.data?.meal!!.get(A) == null){
                                mealsDataList.add("null")
                            }else{
                                mealsDataList.add(response?.body()?.data?.meal!!.get(A))
                            }
                        }

                        for(A in 0 until mealsDataList.size){
                            if(mealsDataList.get(A).equals("null")){
                                continue
                            }else{
                                when (A) {
                                    0-> breakfastList = mealsDataList.get(A).split("<br/>") as ArrayList<String>
                                    1-> lunchList = mealsDataList.get(A).split("<br/>") as ArrayList<String>
                                    2-> dinnerList = mealsDataList.get(A).split("<br/>") as ArrayList<String>
                                }
                            }
                        }

                        onSuccessEvent.call()
                    }
                } else if(response.code() == 400){
                    Log.e("Status[400]", "검증 오류가 발생하였습니다.")
                } else if(response.code() == 404){
                    Log.e("Status[404]", "급식 정보가 존재하지 않습니다.")
                } else{
                    Log.e("Status[500]", "서버 오류가 발생하였습니다.")
                }
            }
        })
    }
}