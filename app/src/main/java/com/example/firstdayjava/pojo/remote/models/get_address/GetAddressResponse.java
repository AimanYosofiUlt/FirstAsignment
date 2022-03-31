package com.example.firstdayjava.pojo.remote.models.get_address;

import com.example.firstdayjava.pojo.remote.callpack.ResponseObject;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAddressResponse extends ResponseObject {
    @SerializedName("Data")
   List<GetAddressData> data;

    public GetAddressResponse(Result result, List<GetAddressData> data) {
        super(result);
        this.data = data;
    }

    public List<GetAddressData> getData() {
        return data;
    }

    public void setData(List<GetAddressData> data) {
        this.data = data;
    }
}
