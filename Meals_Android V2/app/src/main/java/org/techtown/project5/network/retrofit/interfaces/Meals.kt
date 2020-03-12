package org.techtown.project5.network.retrofit.interfaces

import org.techtown.project5.network.Data
import org.techtown.project5.network.Response
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