package com.example.firstdayjava.pojo.remote.models.product;

import java.util.List;

public class Item {
    String itemName;
    String brand;
    String brandF;
    int price;
    String description;
    String packageWeight;
    String currencyCode;
    int inStock;
    float discount;
    String itemCode;
    String brandDescription;
    String descriptionF;
    String brandDescriptionF;
    int tax;
    int deliveryCharge;
    int inactive;
    String itemNameF;
    String categoryCode;
    String subCategoryCode;
    String categoryName;
    String subcategoryName;
    List<ItemImage> images;
    List<Variant> variants;

    public Item() {
    }

    public Item(String itemName, String brand, String brandF, int price, String description, String packageWeight, String currencyCode, int inStock, float discount, String itemCode, String brandDescription, String descriptionF, String brandDescriptionF, int tax, int deliveryCharge, int inactive, String itemNameF, String categoryCode, String subCategoryCode, String categoryName, String subcategoryName, List<ItemImage> images, List<Variant> variants) {
        this.itemName = itemName;
        this.brand = brand;
        this.brandF = brandF;
        this.price = price;
        this.description = description;
        this.packageWeight = packageWeight;
        this.currencyCode = currencyCode;
        this.inStock = inStock;
        this.discount = discount;
        this.itemCode = itemCode;
        this.brandDescription = brandDescription;
        this.descriptionF = descriptionF;
        this.brandDescriptionF = brandDescriptionF;
        this.tax = tax;
        this.deliveryCharge = deliveryCharge;
        this.inactive = inactive;
        this.itemNameF = itemNameF;
        this.categoryCode = categoryCode;
        this.subCategoryCode = subCategoryCode;
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
        this.images = images;
        this.variants = variants;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandF() {
        return brandF;
    }

    public void setBrandF(String brandF) {
        this.brandF = brandF;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(String packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getDescriptionF() {
        return descriptionF;
    }

    public void setDescriptionF(String descriptionF) {
        this.descriptionF = descriptionF;
    }

    public String getBrandDescriptionF() {
        return brandDescriptionF;
    }

    public void setBrandDescriptionF(String brandDescriptionF) {
        this.brandDescriptionF = brandDescriptionF;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public int getInactive() {
        return inactive;
    }

    public void setInactive(int inactive) {
        this.inactive = inactive;
    }

    public String getItemNameF() {
        return itemNameF;
    }

    public void setItemNameF(String itemNameF) {
        this.itemNameF = itemNameF;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public List<ItemImage> getImages() {
        return images;
    }

    public void setImages(List<ItemImage> images) {
        this.images = images;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
