package org.techtown.project5.Network.retrofit.interfaces;

import org.techtown.project5.Network.Data;
import org.techtown.project5.Network.response.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Meals {

    @GET("/meal")
    Call<Response<Data>> GetMeals(@Query("school_id") String school_id,
                                  @Query("office_id") String office_id);
}
