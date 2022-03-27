package com.example.firstdayjava.pojo.dbs.models.responses.datas;

import com.example.firstdayjava.pojo.dbs.models.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryData {
    @SerializedName("Categories")
    List<Category> category;

    @SerializedName("Slider")
    List<AdsSlider> adsSlider;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<AdsSlider> getAdsSlider() {
        return adsSlider;
    }

    public void setAdsSlider(List<AdsSlider> adsSlider) {
        this.adsSlider = adsSlider;
    }
}
