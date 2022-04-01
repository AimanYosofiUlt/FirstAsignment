package com.example.firstdayjava.pojo.remote.models.getOrder;

public class GetOrderPostBody {
    String userCode;

    public GetOrderPostBody() {
    }

    public GetOrderPostBody(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
