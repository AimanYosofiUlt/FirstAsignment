package com.example.firstdayjava.pojo.remote.models.orde_detile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailsList {
    @SerializedName("OrderDetails")
    List<OrderDetailsData> dataList;

    public OrderDetailsList() {
    }

    public OrderDetailsList(List<OrderDetailsData> dataList) {
        this.dataList = dataList;
    }

    public List<OrderDetailsData> getDataList() {
        return dataList;
    }

    public void setDataList(List<OrderDetailsData> dataList) {
        this.dataList = dataList;
    }
}
