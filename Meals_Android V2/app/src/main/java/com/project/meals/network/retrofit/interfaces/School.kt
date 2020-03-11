package com.project.meals.network.retrofit.interfaces

import com.project.meals.network.Data
import com.project.meals.network.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface School {

    @GET("search-school")
    fun searchSchool(@Query("school_name") schoolName : String) : Call<Response<Data>>
}