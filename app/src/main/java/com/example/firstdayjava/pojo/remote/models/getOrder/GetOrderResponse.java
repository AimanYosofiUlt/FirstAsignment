package com.example.firstdayjava.pojo.remote.models.getOrder;

import com.example.firstdayjava.pojo.remote.callpack.ResponseObject;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.google.gson.annotations.SerializedName;

public class GetOrderResponse extends ResponseObject {
    @SerializedName("Data")
    GetOrderData data;

    public GetOrderResponse(Result result) {
        super(result);
    }

    public GetOrderResponse(Result result, GetOrderData data) {
        super(result);
        this.data = data;
    }

    public GetOrderData getData() {
        return data;
    }

    public void setData(GetOrderData data) {
        this.data = data;
    }
}
