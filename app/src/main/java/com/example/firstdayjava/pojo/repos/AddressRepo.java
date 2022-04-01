package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.remote.api.UltimateApi;
import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.models.edit_adress.AddAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.edit_adress.DeleteAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.edit_adress.UpdateAddressPostBody;
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

    public void updateAddress(UpdateAddressPostBody updatePostBody, ResponsesCallBack<BaseResponse> callBack) {
        api.updateAddress(updatePostBody).enqueue(callBack);
    }

    public void deleteAddress(DeleteAddressPostBody postBody, ResponsesCallBack<BaseResponse> callBack) {
        api.deleteAddress(postBody).enqueue(callBack);
    }
}
