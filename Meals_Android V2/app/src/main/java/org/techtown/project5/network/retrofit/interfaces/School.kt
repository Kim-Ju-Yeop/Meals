package org.techtown.project5.network.retrofit.interfaces

import org.techtown.project5.network.Data
import org.techtown.project5.network.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface School {

    @GET("search-school")
    fun searchSchool(@Query("school_name") schoolName : String) : Call<Response<Data>>
}