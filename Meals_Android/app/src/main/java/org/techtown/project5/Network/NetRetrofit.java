package org.techtown.project5.Network;

import org.techtown.project5.Network.retrofit.interfaces.Meals;
import org.techtown.project5.Network.retrofit.interfaces.School;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {
    private static NetRetrofit ourInstance = new NetRetrofit();
    public static NetRetrofit getInstance() {
        return ourInstance;
    }

    private NetRetrofit() {

    }

    Retrofit retrofit = new Retrofit.Builder()

            .baseUrl("http://10.80.162.39:3000")
            .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
            .build();

    School school = retrofit.create(School.class);

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }


    Meals meals = retrofit.create(Meals.class);

    public Meals getMeals() {
        return meals;
    }

    public void setMeals(Meals meals) {
        this.meals = meals;
    }
}

