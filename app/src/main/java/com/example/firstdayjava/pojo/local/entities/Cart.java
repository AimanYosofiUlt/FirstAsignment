package com.example.firstdayjava.pojo.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Cart {
    @NonNull
    @PrimaryKey
    String itemCode = "";
    String userCode;
    int quantity;

    public Cart() {
    }

    @Ignore
    public Cart(@NonNull String itemCode, String userCode, int quantity) {
        this.itemCode = itemCode;
        this.userCode = userCode;
        this.quantity = quantity;
    }

    @NonNull
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(@NonNull String itemCode) {
        this.itemCode = itemCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
