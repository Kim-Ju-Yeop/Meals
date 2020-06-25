package org.techtown.project5.network

import org.techtown.project5.network.retrofit.interfaces.Meals
import org.techtown.project5.network.retrofit.interfaces.School
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit {
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://101.101.211.188:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val school : School = retrofit.create(
        School::class.java)
    val meals : Meals = retrofit.create(
        Meals::class.java)

    companion object {
        val instance = NetRetrofit()
    }
}