package com.example.firstdayjava.pojo.remote.models.product;

import com.example.firstdayjava.pojo.remote.callpack.ResponseObject;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.google.gson.annotations.SerializedName;

public class ProductResponse extends ResponseObject {
    @SerializedName("Data")
    ProductData data;

    public ProductResponse(Result result, ProductData data) {
        super(result);
        this.data = data;
    }

    public ProductData getData() {
        return data;
    }

    public void setData(ProductData data) {
        this.data = data;
    }
}
