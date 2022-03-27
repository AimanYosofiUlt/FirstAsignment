package com.example.firstdayjava.pojo.dbs.models.responses.callpack;

import com.google.gson.annotations.SerializedName;

public class ResponseObject {
    @SerializedName(value = "Result")
    Result result;

    public ResponseObject(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
