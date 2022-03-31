package com.example.firstdayjava.pojo.remote.callpack;

public class ResponseState {
    Boolean isSuccessful;
    String message;

    public ResponseState() {
        this.isSuccessful = true;
        this.message = "DONE";
    }

    public ResponseState(String message) {
        this.isSuccessful = false;
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
