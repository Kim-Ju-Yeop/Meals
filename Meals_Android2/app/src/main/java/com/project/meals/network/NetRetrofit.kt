package com.project.meals.network

import com.project.meals.network.retrofit.interfaces.Meals
import com.project.meals.network.retrofit.interfaces.School
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit {
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.25.8:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val school : School = retrofit.create(School::class.java)
    val meals : Meals = retrofit.create(Meals::class.java)

    companion object {
        val instance = NetRetrofit()
    }
}