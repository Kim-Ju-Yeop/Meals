package com.project.meals.network

import com.project.meals.network.retrofit.interfaces.School
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit {
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-13-209-77-143.ap-northeast-2.compute.amazonaws.com:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val school : School = retrofit.create(School::class.java)

    companion object {
        val instance = NetRetrofit()
    }
}