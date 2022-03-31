package com.example.firstdayjava.pojo.remote.api;

import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.models.add_adress.AddAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.category.MainPagePostBody;
import com.example.firstdayjava.pojo.remote.models.category.MainPageResponse;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressResponse;
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
    Call<LoginResponse> login(@Body LoginPostBody postBody);

    @POST("user/signup")
    Call<BaseResponse> signup(@Body SignUpPostBody postBody);

    @POST("item/category")
    Call<MainPageResponse> getCategories(@Body MainPagePostBody postBody);

    @POST("item/listing")
    Call<ProductResponse> getProducts(@Body ProductPostBody postBody);

    @POST("user/addAddress")
    Call<BaseResponse> addAddress(@Body AddAddressPostBody postBody);

    @POST("user/userAddresses")
    Call<GetAddressResponse> getAddress(@Body GetAddressPostBody postBody);
}
