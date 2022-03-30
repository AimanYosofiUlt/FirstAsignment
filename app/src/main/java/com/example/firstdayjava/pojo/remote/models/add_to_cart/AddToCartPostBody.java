package com.example.firstdayjava.pojo.remote.models.add_to_cart;

import com.google.gson.annotations.SerializedName;

public class AddToCartPostBody {
    @SerializedName("itemCode")
    String itemCode;
    String userCode;

    @SerializedName("ItemQty")
    int quantity;
    int price;
    int discount = 0;
    int tax = 0;
    int deliveryCharge = 0;

    public AddToCartPostBody() {
    }

    public AddToCartPostBody(String itemCode, String userCode, int quantity, int price) {
        this.itemCode = itemCode;
        this.userCode = userCode;
        this.quantity = quantity;
        this.price = price;
    }

    public String getitemCode() {
        return itemCode;
    }

    public void setitemCode(String itemCode) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }
}
