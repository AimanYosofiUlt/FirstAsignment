package com.example.firstdayjava.pojo.remote.models.product;

public class Variant {
    String colorName;
    int colorID;
    String sizeName;
    int sizeID;

    public Variant() {
    }

    public Variant(String colorName, int colorID, String sizeName, int sizeID) {
        this.colorName = colorName;
        this.colorID = colorID;
        this.sizeName = sizeName;
        this.sizeID = sizeID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }
}
