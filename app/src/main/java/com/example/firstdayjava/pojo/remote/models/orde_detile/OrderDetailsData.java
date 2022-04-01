package com.example.firstdayjava.pojo.remote.models.orde_detile;

public class OrderDetailsData {
    String price;
    String discount;
    String tax;
    String deliveryCharge;
    String ItemQty;
    String itemCode;
    String itemName;
    String itemNameF;

    public OrderDetailsData() {
    }

    public OrderDetailsData(
            String price, String discount, String tax,
            String deliveryCharge, String itemQty, String itemCode,
            String itemName, String itemNameF) {
        this.price = price;
        this.discount = discount;
        this.tax = tax;
        this.deliveryCharge = deliveryCharge;
        ItemQty = itemQty;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemNameF = itemNameF;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getItemQty() {
        return ItemQty;
    }

    public void setItemQty(String itemQty) {
        ItemQty = itemQty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNameF() {
        return itemNameF;
    }

    public void setItemNameF(String itemNameF) {
        this.itemNameF = itemNameF;
    }
}
