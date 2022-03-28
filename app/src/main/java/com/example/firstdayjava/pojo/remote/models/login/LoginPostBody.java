package com.example.firstdayjava.pojo.remote.models.login;

import com.example.firstdayjava.pojo.remote.models.category.MainPagePostBody;
import com.google.gson.annotations.SerializedName;

public class LoginPostBody extends MainPagePostBody {

    @SerializedName("user_phone")
    private String phone;
    @SerializedName("login_password")
    private String password;

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
