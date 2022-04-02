package com.example.firstdayjava.pojo.remote.api;

import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.models.cahnge_password.ChangePasswordPostBody;
import com.example.firstdayjava.pojo.remote.models.category.MainPagePostBody;
import com.example.firstdayjava.pojo.remote.models.category.MainPageResponse;
import com.example.firstdayjava.pojo.remote.models.edit_adress.AddAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.edit_adress.DeleteAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.edit_adress.UpdateAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.edit_profile.EditProfilePostBody;
import com.example.firstdayjava.pojo.remote.models.getOrder.GetOrderPostBody;
import com.example.firstdayjava.pojo.remote.models.getOrder.GetOrderResponse;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressResponse;
import com.example.firstdayjava.pojo.remote.models.login.LoginPostBody;
import com.example.firstdayjava.pojo.remote.models.login.LoginResponse;
import com.example.firstdayjava.pojo.remote.models.orde_detile.OrderDetailResponse;
import com.example.firstdayjava.pojo.remote.models.orde_detile.OrderPostBody;
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

    @POST("user/updateProfile")
    Call<BaseResponse> editProfile(@Body EditProfilePostBody postBody);

    @POST("user/changePassword")
    Call<BaseResponse> changePassword(@Body ChangePasswordPostBody postBody);

    @POST("user/addAddress")
    Call<BaseResponse> addAddress(@Body AddAddressPostBody postBody);

    @POST("user/updateAddress")
    Call<BaseResponse> updateAddress(@Body UpdateAddressPostBody postBody);

    @POST("user/deleteAddress")
    Call<BaseResponse> deleteAddress(@Body DeleteAddressPostBody postBody);

    @POST("user/userAddresses")
    Call<GetAddressResponse> getAddress(@Body GetAddressPostBody postBody);

    @POST("item/category")
    Call<MainPageResponse> getCategories(@Body MainPagePostBody postBody);

    @POST("item/listing")
    Call<ProductResponse> getProducts(@Body ProductPostBody postBody);

    @POST("user/getOrder")
    Call<GetOrderResponse> getOrders(@Body GetOrderPostBody postBody);

    @POST("user/getOrderDetails")
    Call<OrderDetailResponse> getDetailedOrder(@Body OrderPostBody postBody);

    @POST("user/cancelOrder")
    Call<BaseResponse> cancelOrder(@Body OrderPostBody postBody);
}
