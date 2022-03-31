package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.remote.api.UltimateApi;
import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.add_adress.AddAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressResponse;

import javax.inject.Inject;

public class AddressRepo {
    @Inject
    UltimateApi api;

    @Inject
    AddressRepo(){

    }

    public void addAddress(AddAddressPostBody postBody, ResponsesCallBack<BaseResponse> callBack) {
        api.addAddress(postBody).enqueue(callBack);
    }

    public void getAddress(GetAddressPostBody postBody, ResponsesCallBack<GetAddressResponse> callBack) {
        api.getAddress(postBody).enqueue(callBack);
    }
}
