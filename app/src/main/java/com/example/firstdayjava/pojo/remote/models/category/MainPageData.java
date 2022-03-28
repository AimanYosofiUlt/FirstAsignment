package com.example.firstdayjava.pojo.remote.models.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainPageData {
    @SerializedName("Categories")
    List<CategoryData> category;

    @SerializedName("Slider")
    List<AdsSlider> adsSlider;

    public List<CategoryData> getCategories() {
        return category;
    }

    public void setCategory(List<CategoryData> category) {
        this.category = category;
    }

    public List<AdsSlider> getAdsSliders() {
        return adsSlider;
    }

    public void setAdsSlider(List<AdsSlider> adsSlider) {
        this.adsSlider = adsSlider;
    }
}
