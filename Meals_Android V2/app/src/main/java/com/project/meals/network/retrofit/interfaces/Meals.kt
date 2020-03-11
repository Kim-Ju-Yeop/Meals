package com.project.meals.network.retrofit.interfaces

import com.project.meals.network.Data
import com.project.meals.network.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Meals {

    @GET("v2/meal/yesterday")
    fun getYesterday(@Query("school_id") school_id : String, @Query("office_id") office_id : String): Call<Response<Data>>

    @GET("v2/meal/today")
    fun getToday(@Query("school_id") school_id : String, @Query("office_id") office_id : String): Call<Response<Data>>

    @GET("v2/meal/tomorrow")
    fun getTomorrow(@Query("school_id") school_id : String, @Query("office_id") office_id : String): Call<Response<Data>>
}