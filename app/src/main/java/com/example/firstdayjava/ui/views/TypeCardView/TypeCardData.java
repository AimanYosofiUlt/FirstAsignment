package com.example.firstdayjava.ui.views.TypeCardView;

public class TypeCardData {
    String name;
    public boolean isChecked;


    public TypeCardData(String name) {
        this.name = name;
        isChecked = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
