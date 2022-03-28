package com.example.firstdayjava.pojo.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    String categoryName;
    String imageUrl;

    @NonNull
    @PrimaryKey
    String categoryCode = "";

    public Category() {
    }

    @Ignore
    public Category(String categoryName, String imageUrl, @NonNull String categoryCode) {
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(@NonNull String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
