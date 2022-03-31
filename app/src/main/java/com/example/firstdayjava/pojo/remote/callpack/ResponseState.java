package com.example.firstdayjava.pojo.remote.callpack;

public class ResponseState {
    Boolean isSuccessful;
    String message;

    public ResponseState(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public ResponseState(Boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public Boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
