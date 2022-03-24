package com.example.firstdayjava.pojo.dbs.models;

import com.google.gson.annotations.SerializedName;

public class SubCategory {
    String imagedef;
    String imageName;
    @SerializedName("subcategoryName")
    String name;
    @SerializedName("subcategoryName")
    String fName;
    @SerializedName("subcategoryName")
    String code;
    String inactive;
    String imageUrl;

    public SubCategory() {
    }

    public SubCategory(String imagedef, String imageName, String name, String fName, String code, String inactive, String imageUrl) {
        this.imagedef = imagedef;
        this.imageName = imageName;
        this.name = name;
        this.fName = fName;
        this.code = code;
        this.inactive = inactive;
        this.imageUrl = imageUrl;
    }

    public String getImagedef() {
        return imagedef;
    }

    public void setImagedef(String imagedef) {
        this.imagedef = imagedef;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
