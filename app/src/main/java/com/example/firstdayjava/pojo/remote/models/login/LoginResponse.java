package com.example.firstdayjava.pojo.remote.models.login;

import com.example.firstdayjava.pojo.remote.models.login.LoginData;
import com.example.firstdayjava.pojo.remote.callpack.ResponseObject;
import com.example.firstdayjava.pojo.remote.callpack.Result;
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

