package com.example.firstdayjava.pojo.remote.models.product;

public class ItemImage {
    int imageID;
    String imageUrl;;
    String imageName;
    int imagedef;

    public ItemImage() {
    }

    public ItemImage(int imageID, String imageUrl, String imageName, int imagedef) {
        this.imageID = imageID;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.imagedef = imagedef;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImagedef() {
        return imagedef;
    }

    public void setImagedef(int imagedef) {
        this.imagedef = imagedef;
    }
}
