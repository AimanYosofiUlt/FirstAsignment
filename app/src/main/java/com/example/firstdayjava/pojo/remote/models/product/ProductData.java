package com.example.firstdayjava.pojo.remote.models.product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductData {
    @SerializedName("Items")
    List<Item> items;

    public ProductData() {
    }

    public ProductData(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
