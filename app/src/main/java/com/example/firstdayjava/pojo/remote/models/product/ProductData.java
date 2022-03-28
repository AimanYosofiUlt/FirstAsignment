package com.example.firstdayjava.pojo.remote.models.product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductData {
    @SerializedName("Items")
    List<Item> items;
}
