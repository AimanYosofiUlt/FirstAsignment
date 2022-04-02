package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.remote.api.UltimateApi;
import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.getOrder.GetOrderPostBody;
import com.example.firstdayjava.pojo.remote.models.getOrder.GetOrderResponse;
import com.example.firstdayjava.pojo.remote.models.orde_detile.OrderPostBody;
import com.example.firstdayjava.pojo.remote.models.orde_detile.OrderDetailResponse;

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

    public void getOrderDetails(OrderPostBody postBody, ResponsesCallBack<OrderDetailResponse> callBack) {
        api.getDetailedOrder(postBody).enqueue(callBack);

    }

    public void cancleOrder(OrderPostBody postBody, ResponsesCallBack<BaseResponse> callBack) {
        api.cancelOrder(postBody).enqueue(callBack);
    }
}
