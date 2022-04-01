package com.example.firstdayjava.pojo.remote.models.orde_detile;

import com.example.firstdayjava.pojo.remote.callpack.ResponseObject;
import com.example.firstdayjava.pojo.remote.callpack.Result;

public class OrderDetailPostBody {
    String orderID;

    public OrderDetailPostBody() {
    }

    public OrderDetailPostBody(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
