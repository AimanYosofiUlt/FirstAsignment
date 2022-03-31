package com.example.firstdayjava.ui.views.ProductView;

import androidx.room.Ignore;

import com.example.firstdayjava.pojo.local.entities.Product;

public class ProductViewData extends Product {
    public int amount;

    public ProductViewData() {
    }

    @Ignore
    public ProductViewData(String itemCode, String name, Integer price, String description, String currencyCode, String descriptionF, String itemNameF, String categoryCode, String subCategoryCode, String imageUrl, int amount) {
        super(itemCode, name, price, description, currencyCode, descriptionF, itemNameF, categoryCode, subCategoryCode, imageUrl);
        this.amount = amount;
    }

    @Ignore
    public ProductViewData(Product product, int amount) {
        super(product.getItemCode(), product.getName(), product.getPrice(),
                product.getDescription(), product.getCurrencyCode(), product.getDescriptionF(),
                product.getItemNameF(), product.getCategoryCode(), product.getSubCategoryCode(),
                product.getImageUrl());

        this.amount = amount;
    }

    public Product getProduct(){
        return new Product(
                getItemCode(),getName(),getPrice(),getDescription(),
                getCurrencyCode(),getDescriptionF(),getItemNameF(),getCategoryCode(),
                getSubCategoryCode(),getImageUrl()
        );
    }

    public int getAmount() {
        return amount;
    }
}
