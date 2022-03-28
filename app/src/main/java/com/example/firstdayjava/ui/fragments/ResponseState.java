package com.example.firstdayjava.ui.fragments;

public class ResponseState {
    Boolean isSuccssful;
    String message;

    public ResponseState(Boolean isSuccssful) {
        this.isSuccssful = isSuccssful;
    }

    public ResponseState(Boolean isSuccssful, String message) {
        this.isSuccssful = isSuccssful;
        this.message = message;
    }

    public Boolean isSuccssful() {
        return isSuccssful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
