package com.example.firstdayjava.pojo.dbs.models.responses.datas;

import com.google.gson.annotations.SerializedName;

public class LoginPostBody {

    @SerializedName("user_phone")
    private String phone;
    @SerializedName("login_password")
    private String password;
    public String device_description ="12";
    public String device_token ="12";
    public String device_type ="12";
    public String device_serial ="12";

    public LoginPostBody(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
