package com.example.firstdayjava.pojo.remote.models.get_address;

public class GetAddressPostBody {
    String userCode;

    public GetAddressPostBody() {
    }

    public GetAddressPostBody(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
