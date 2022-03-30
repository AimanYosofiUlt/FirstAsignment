package com.example.firstdayjava.ui.views.SubCategoryCardView;

import com.example.firstdayjava.pojo.local.entities.SubCategory;

public class SubCategoryCard {
    SubCategory subCategory;
    public boolean isChecked;


    public SubCategoryCard(SubCategory name) {
        this.subCategory = name;
        isChecked = false;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
