package org.techtown.project5.Network.retrofit.interfaces;

import org.techtown.project5.Network.Data;
import org.techtown.project5.Network.response.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface School {

    @GET("/search-school")
    Call<Response<Data>> searchSchool(@Query("school_name") String SchoolName);

}
