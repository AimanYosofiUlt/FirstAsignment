package com.example.firstdayjava.pojo.remote.models.product;

public class ProductPostBody {

    Value value;

    public ProductPostBody() {
        value = new Value();
    }

    public ProductPostBody(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

}

class Value {
    String categoryCode = "1";
    String subCategoryCode = "1";

    public Value() {
    }

    public Value(String categoryCode, String subCategoryCode) {
        this.categoryCode = categoryCode;
        this.subCategoryCode = subCategoryCode;
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
}
