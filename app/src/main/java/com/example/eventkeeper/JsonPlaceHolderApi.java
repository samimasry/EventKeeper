package com.example.eventkeeper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("signup")
    Call<User> createUser(@Body User user);
    @POST("signin")
    Call<User> loginUser(@Body User user);
}
