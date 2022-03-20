package com.example.firstdayjava.ui.views.ProductView;

public class ProductCardData {
    String name;
    int price;
    public boolean isChecked;

    public ProductCardData(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
