package com.example.firstdayjava.pojo.remote.models.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryData {
    int imagedef;
    String imageName;
    String imageUrl;
    @SerializedName("categoryName")
    String name;
    @SerializedName("categoryFname")
    String fName;
    @SerializedName("categoryCode")
    String code;
    int inactive;

    @SerializedName("subCategories")
    List<SubCategoryData> subCategories;

    public CategoryData() {

    }

    public CategoryData(int imagedef, String imageName, String imageUrl, String name, String fName, String code, int inactive, List<SubCategoryData> subCategories) {
        this.imagedef = imagedef;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.name = name;
        this.fName = fName;
        this.code = code;
        this.inactive = inactive;
        this.subCategories = subCategories;
    }

    public int getImagedef() {
        return imagedef;
    }

    public void setImagedef(int imagedef) {
        this.imagedef = imagedef;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getInactive() {
        return inactive;
    }

    public void setInactive(int inactive) {
        this.inactive = inactive;
    }

    public List<SubCategoryData> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryData> subCategories) {
        this.subCategories = subCategories;
    }
}
