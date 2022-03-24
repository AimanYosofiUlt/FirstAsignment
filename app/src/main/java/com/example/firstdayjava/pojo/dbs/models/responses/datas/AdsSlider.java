package com.example.firstdayjava.pojo.dbs.models.responses.datas;

import java.util.List;

public class AdsSlider {
    int sliderCode;
    int hasActions;
    String actionUrl;
    int categoryCode;
    String categoryName;
    String categoryFName;
    int inactive;
    int imagedef;
    String imageName;
    String imageID;
    String imageUrl;
    List<String> Images;

    public AdsSlider() {
    }

    public AdsSlider(int sliderCode, int hasActions, String actionUrl, int categoryCode, String categoryName, String categoryFName, int inactive, int imagedef, String imageName, String imageID, String imageUrl, List<String> images) {
        this.sliderCode = sliderCode;
        this.hasActions = hasActions;
        this.actionUrl = actionUrl;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.categoryFName = categoryFName;
        this.inactive = inactive;
        this.imagedef = imagedef;
        this.imageName = imageName;
        this.imageID = imageID;
        this.imageUrl = imageUrl;
        Images = images;
    }

    public int getSliderCode() {
        return sliderCode;
    }

    public void setSliderCode(int sliderCode) {
        this.sliderCode = sliderCode;
    }

    public int getHasActions() {
        return hasActions;
    }

    public void setHasActions(int hasActions) {
        this.hasActions = hasActions;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryFName() {
        return categoryFName;
    }

    public void setCategoryFName(String categoryFName) {
        this.categoryFName = categoryFName;
    }

    public int getInactive() {
        return inactive;
    }

    public void setInactive(int inactive) {
        this.inactive = inactive;
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

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getImages() {
        return Images;
    }

    public void setImages(List<String> images) {
        Images = images;
    }
}
