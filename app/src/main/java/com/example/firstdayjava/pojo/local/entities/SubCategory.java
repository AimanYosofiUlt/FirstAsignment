package com.example.firstdayjava.pojo.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class SubCategory {
    @NonNull
    @PrimaryKey
    String code = "";
    String name;
    String imageUrl;

    @NonNull
    String categoryCode = "";

    public SubCategory() {
    }

    @Ignore
    public SubCategory(@NonNull String code, String name, String imageUrl, @NonNull String categoryCode) {
        this.code = code;
        this.name = name;
        this.imageUrl = imageUrl;
        this.categoryCode = categoryCode;
    }




    @NonNull
    public String getCode() {
        return code;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
