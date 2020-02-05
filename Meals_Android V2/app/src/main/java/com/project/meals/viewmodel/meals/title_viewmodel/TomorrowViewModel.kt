package com.project.meals.viewmodel.meals.title_viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.meals.network.Data
import com.project.meals.network.NetRetrofit
import com.project.meals.network.response.Response
import com.project.meals.viewmodel.base.BaseViewModel
import com.project.meals.widget.SingleLiveEvent
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import java.lang.NullPointerException

class TomorrowViewModel : BaseViewModel() {

    // Meals Server Data
    fun getMeals(school_id : String, office_id : String){
        val res : Call<Response<Data>> = netRetrofit.meals.getTomorrow(school_id, office_id)
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
                            onBreakfastEvent.call()
                        }
                    }
                    404 -> onBreakfastEvent.call()
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getMeals[Error]", "급식 조회 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }

    // Next & Back
    fun nextMeals(){
        if(checkCount == 3) onFailEvent.call()
        else{
            checkCount++
            when(checkCount){
                1 -> onBreakfastEvent.call()
                2 -> onLunchEvent.call()
                3 -> onDinnerEvent.call()
            }
        }
    }
    fun backMeals(){
        if(checkCount == 1) onFailEvent.call()
        else{
            checkCount--
            when(checkCount){
                1 -> onBreakfastEvent.call()
                2 -> onLunchEvent.call()
                3 -> onDinnerEvent.call()
            }
        }
    }
}