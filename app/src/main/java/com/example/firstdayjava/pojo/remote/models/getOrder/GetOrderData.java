package com.example.firstdayjava.pojo.remote.models.getOrder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOrderData {
    @SerializedName("OrderMaster")
    List<OrderMaster> orderMasterList;

    public GetOrderData() {
    }

    public GetOrderData(List<OrderMaster> orderMasterList) {
        this.orderMasterList = orderMasterList;
    }

    public List<OrderMaster> getOrderMasterList() {
        return orderMasterList;
    }

    public void setOrderMasterList(List<OrderMaster> orderMasterList) {
        this.orderMasterList = orderMasterList;
    }
}

