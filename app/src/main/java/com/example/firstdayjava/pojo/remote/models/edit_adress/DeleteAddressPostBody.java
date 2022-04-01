package com.example.firstdayjava.pojo.remote.models.edit_adress;

public class DeleteAddressPostBody {
    String addressID;

    public DeleteAddressPostBody() {
    }

    public DeleteAddressPostBody(String addressID) {
        this.addressID = addressID;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }
}
