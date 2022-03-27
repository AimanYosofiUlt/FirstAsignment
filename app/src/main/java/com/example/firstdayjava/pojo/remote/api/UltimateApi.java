package com.example.firstdayjava.pojo.remote.api;

import com.example.firstdayjava.pojo.dbs.models.responses.CategoryResponse;
import com.example.firstdayjava.pojo.dbs.models.responses.datas.LoginPostBody;
import com.example.firstdayjava.pojo.dbs.models.responses.LoginResponse;
import com.example.firstdayjava.pojo.dbs.models.responses.SignUpResponse;
import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.pojo.dbs.models.responses.datas.PostBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UltimateApi {

    @POST("user/login")
    Call<LoginResponse> login(@Body LoginPostBody loginPost);

    @POST("user/signup")
    Call<SignUpResponse> signup(@Body Users user);

    @POST("item/category")
    Call<CategoryResponse> getCategories(@Body PostBody postBody);
}
