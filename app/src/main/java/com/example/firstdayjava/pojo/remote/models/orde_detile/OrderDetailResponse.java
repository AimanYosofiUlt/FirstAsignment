package com.example.firstdayjava.pojo.remote.models.orde_detile;

import com.example.firstdayjava.pojo.remote.callpack.ResponseObject;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.google.gson.annotations.SerializedName;

public class OrderDetailResponse extends ResponseObject {

    @SerializedName("Data")
    OrderDetailsList data;

    public OrderDetailResponse(Result result, OrderDetailsList data) {
        super(result);
        this.data = data;
    }

    public OrderDetailsList getData() {
        return data;
    }

    public void setData(OrderDetailsList data) {
        this.data = data;
    }
}
