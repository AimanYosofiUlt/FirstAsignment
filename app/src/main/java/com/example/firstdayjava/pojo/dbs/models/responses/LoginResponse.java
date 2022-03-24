package com.example.firstdayjava.pojo.dbs.models.responses;

import com.example.firstdayjava.pojo.dbs.models.responses.datas.LoginData;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.ResponseObject;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.Result;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends ResponseObject {
    @SerializedName("Data")
    LoginData data;

    public LoginResponse(LoginData data, Result result) {
        super(result);
        this.data = data;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}

