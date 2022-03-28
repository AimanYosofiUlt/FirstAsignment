package com.example.firstdayjava.pojo.remote.models.category;

import com.example.firstdayjava.pojo.remote.models.category.MainPageData;
import com.example.firstdayjava.pojo.remote.callpack.ResponseObject;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.google.gson.annotations.SerializedName;

public class MainPageResponse extends ResponseObject {
    @SerializedName("Data")
    MainPageData data;

    public MainPageResponse(Result result, MainPageData data) {
        super(result);
        this.data = data;
    }

    public MainPageResponse(Result result) {
        super(result);
    }

    public MainPageData getData() {
        return data;
    }

    public void setData(MainPageData data) {
        this.data = data;
    }
}
