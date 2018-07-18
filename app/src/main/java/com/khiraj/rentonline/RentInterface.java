package com.khiraj.rentonline;

import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.GET;


public interface RentInterface {

    @GET("Rest/getCategories")
    Call<Rent_main_pojo> getData();

    @GET("Rest/getsubCategories")
    Call<SubRent_main_pojo> getSubCategoriesData();
}
