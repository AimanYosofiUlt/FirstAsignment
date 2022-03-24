package com.example.firstdayjava.pojo.dbs.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {
    String imagedef;
    String imageName;
    String imageUrl;
    @SerializedName("categoryName")
    String name;
    @SerializedName("categoryFname")
    String fName;
    @SerializedName("categoryCode")
    String code;
    String inactive;
    List<SubCategory> subCategories;

    public Category() {

    }

    public Category(String imagedef, String imageName, String imageUrl, String name, String fName, String code, String inactive, List<SubCategory> subCategories) {
        this.imagedef = imagedef;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.name = name;
        this.fName = fName;
        this.code = code;
        this.inactive = inactive;
        this.subCategories = subCategories;
    }

    public void setImagedef(String imagedef) {
        this.imagedef = imagedef;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public String getImagedef() {
        return imagedef;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getfName() {
        return fName;
    }

    public String getCode() {
        return code;
    }

    public String getInactive() {
        return inactive;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }
}
