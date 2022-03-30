package com.example.firstdayjava.pojo.remote.api;

import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.pojo.local.models.responses.SignUpResponse;
import com.example.firstdayjava.pojo.remote.models.category.MainPagePostBody;
import com.example.firstdayjava.pojo.remote.models.category.MainPageResponse;
import com.example.firstdayjava.pojo.remote.models.login.LoginPostBody;
import com.example.firstdayjava.pojo.remote.models.login.LoginResponse;
import com.example.firstdayjava.pojo.remote.models.product.ProductPostBody;
import com.example.firstdayjava.pojo.remote.models.product.ProductResponse;
import com.example.firstdayjava.pojo.remote.models.signup.SignUpPostBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UltimateApi {

    @POST("user/login")
    Call<LoginResponse> login(@Body LoginPostBody loginPost);

    @POST("user/signup")
    Call<SignUpResponse> signup(@Body SignUpPostBody postBody);

    @POST("item/category")
    Call<MainPageResponse> getCategories(@Body MainPagePostBody postBody);

    @POST("item/listing")
    Call<ProductResponse> getProducts(@Body ProductPostBody postBody);
}
