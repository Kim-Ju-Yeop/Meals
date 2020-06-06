package org.techtown.project5.viewmodel.meals.title_viewmodel

import android.util.Log
import org.techtown.project5.network.Data
import org.techtown.project5.network.Response
import org.techtown.project5.viewmodel.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TodayViewModel : BaseViewModel() {

    // Meals Server Data
    fun getMeals(school_id : String, office_id : String){
        val res : Call<Response<Data>> = netRetrofit.meals.getToday(school_id, office_id)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                when(response.code()){
                    200 ->{
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
                            when(checkCount){
                                1 -> onBreakfastEvent.call()
                                2 -> onLunchEvent.call()
                                3 -> onDinnerEvent.call()
                            }
                        }
                    }
                    404 -> when(checkCount){
                        1 -> onBreakfastEvent.call()
                        2 -> onLunchEvent.call()
                        3 -> onDinnerEvent.call()
                    }
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getMeals[Error]", "급식 조회 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }

    fun checkTime(){
        val formatTime = simpleDateFormat.format(date)
        val hour = Integer.parseInt(formatTime)

        if(hour < 11) checkCount = 1
        else if(hour < 13) checkCount = 2
        else checkCount = 3
    }

    // Next & Back
    fun nextMeals(){
        if(checkCount != 3){
            checkCount++
            when(checkCount){
                1 -> onBreakfastEvent.call()
                2 -> onLunchEvent.call()
                3 -> onDinnerEvent.call()
            }
        }
    }

    fun backMeals(){
        if(checkCount != 1){
            checkCount--
            when(checkCount){
                1 -> onBreakfastEvent.call()
                2 -> onLunchEvent.call()
                3 -> onDinnerEvent.call()
            }
        }
    }
}