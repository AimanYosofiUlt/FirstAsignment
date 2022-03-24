package com.example.firstdayjava.ui.fragments;

public class ResponseState {
    Boolean isLoginDone;
    String message;

    public ResponseState(Boolean isLoginDone) {
        this.isLoginDone = isLoginDone;
    }

    public ResponseState(Boolean isLoginDone, String message) {
        this.isLoginDone = isLoginDone;
        this.message = message;
    }

    public Boolean isLoginDone() {
        return isLoginDone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
