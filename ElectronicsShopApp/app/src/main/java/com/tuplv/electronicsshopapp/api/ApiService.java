package com.tuplv.electronicsshopapp.api;

import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tuplv.electronicsshopapp.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    String url = "http://192.168.1.41:8080/ElectronicsShopBackend/api/";

    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @Multipart
    @POST("user")
    Call<User> updateAccount(@Part("id") RequestBody id,
                             @Part("userName") RequestBody userName,
                             @Part("email") RequestBody email,
                             @Part("password") RequestBody password,
                             @Part("fullName") RequestBody fullName,
                             @Part("gender") RequestBody gender,
                             @Part("birthday") RequestBody birthday,
                             @Part("phone") RequestBody phone,
                             @Part("address") RequestBody address,
                             @Part MultipartBody.Part avatar,
                             @Part("jobs") RequestBody jobs,
                             @Part("facebook") RequestBody facebook
    );
}
