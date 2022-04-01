package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.remote.api.UltimateApi;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.getOrder.GetOrderPostBody;
import com.example.firstdayjava.pojo.remote.models.getOrder.GetOrderResponse;

import javax.inject.Inject;

public class OrderRepo {
    @Inject
    UltimateApi api;


    @Inject
    OrderRepo(){

    }

    public void getOrders(GetOrderPostBody postBody, ResponsesCallBack<GetOrderResponse> callBack) {
        api.getOrders(postBody).enqueue(callBack);
    }
}
