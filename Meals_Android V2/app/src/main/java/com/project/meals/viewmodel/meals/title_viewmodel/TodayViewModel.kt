package com.project.meals.viewmodel.meals.title_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.project.meals.network.Data
import com.project.meals.network.NetRetrofit
import com.project.meals.network.response.Response
import com.project.meals.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class TodayViewModel : ViewModel() {

    val neRetrofit = NetRetrofit()
    val mealsDataList = ArrayList<String>()

    var breakfastList = ArrayList<String>()
    var lunchList = ArrayList<String>()
    var dinnerList = ArrayList<String>()

    val onBreakfastEvent = SingleLiveEvent<Unit>()
    val onLunchEvent = SingleLiveEvent<Unit>()
    val onDinnerEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    var checkCount : Int = 0

    // Meals Server Data
    fun getMeals(school_id : String, office_id : String){
        val res : Call<Response<Data>> = neRetrofit.meals.getToday(school_id, office_id)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("서버 통신 X", "서버 통신을 실패하였습니다.")
            }
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200){
                    if(response.body()?.data?.meal != null){
                        Log.e("Status[200]", "어제 급식 조회를 수행하였습니다.")

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
                        onBreakfastEvent.call()
                    }
                } else if(response.code() == 400){
                    Log.e("Status[400]", "검증 오류가 발생하였습니다.")
                } else if(response.code() == 404){
                    Log.e("Status[404]", "급식 정보가 존재하지 않습니다.")
                    onBreakfastEvent.call()
                } else{
                    Log.e("Status[500]", "서버 오류가 발생하였습니다.")
                }
            }
        })
    }

    // Next & Back
    fun nextMeals(){
        if(checkCount == 3){
            onFailEvent.call()
        }else{
            checkCount++

            when(checkCount){
                1 -> onBreakfastEvent.call()
                2 -> onLunchEvent.call()
                3 -> onDinnerEvent.call()
            }
        }
    }
    fun backMeals(){
        if(checkCount == 1){
            onFailEvent.call()
        } else{
            checkCount--

            when(checkCount){
                1 -> onBreakfastEvent.call()
                2 -> onLunchEvent.call()
                3 -> onDinnerEvent.call()
            }
        }
    }
}