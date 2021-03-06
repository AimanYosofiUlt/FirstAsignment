package com.example.firstdayjava.pojo.local.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import javax.inject.Inject;

@Entity
public class Product {
    @NonNull
    @PrimaryKey()
    String itemCode = "";

    String name;
    Integer price;
    String description;
    String currencyCode;
    String descriptionF;
    String itemNameF;
    String categoryCode;
    String subCategoryCode;
    String imageUrl;

    @ColumnInfo(defaultValue = "0")
    Integer favState;

    public static final int IN_FAV_STATE = 1;
    public static final int NOT_IN_FAV_STATE = 0;

    public Product() {
    }

    @Ignore
    public Product(@NonNull String itemCode, String name, Integer price, String description, String currencyCode, String descriptionF, String itemNameF, String categoryCode, String subCategoryCode, String imageUrl, Integer favState) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.description = description;
        this.currencyCode = currencyCode;
        this.descriptionF = descriptionF;
        this.itemNameF = itemNameF;
        this.categoryCode = categoryCode;
        this.subCategoryCode = subCategoryCode;
        this.imageUrl = imageUrl;
        this.favState = favState;
    }


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescriptionF() {
        return descriptionF;
    }

    public void setDescriptionF(String descriptionF) {
        this.descriptionF = descriptionF;
    }

    public String getItemNameF() {
        return itemNameF;
    }

    public void setItemNameF(String itemNameF) {
        this.itemNameF = itemNameF;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getFavState() {
        return favState;
    }

    public void setFavState(Integer favState) {
        this.favState = favState;
    }
}
