package com.example.firstdayjava.pojo.remote.models.getOrder;

public class OrderMaster {
    String orderID;
    int orderAmount;
    String orderDate;
    int orderDiscount;
    int deliveryCharge;
    int orderTax;
    int orderTotal;
    String status;
    String addressID;
    String paymentMethod;
    String deliveryFlag;
    String notes;

    public OrderMaster() {
    }

    public OrderMaster(String orderID, int orderAmount, String orderDate, int orderDiscount, int deliveryCharge, int orderTax, int orderTotal, String status, String addressID, String paymentMethod, String deliveryFlag, String notes) {
        this.orderID = orderID;
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
        this.orderDiscount = orderDiscount;
        this.deliveryCharge = deliveryCharge;
        this.orderTax = orderTax;
        this.orderTotal = orderTotal;
        this.status = status;
        this.addressID = addressID;
        this.paymentMethod = paymentMethod;
        this.deliveryFlag = deliveryFlag;
        this.notes = notes;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(int orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public int getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(int orderTax) {
        this.orderTax = orderTax;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
