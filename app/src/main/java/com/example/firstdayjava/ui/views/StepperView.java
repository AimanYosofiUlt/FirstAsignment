package com.example.firstdayjava.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewStapperBinding;

public class StepperView {
    ViewStapperBinding bd;

    public StepperView(Context context) {
        bd = ViewStapperBinding.inflate(LayoutInflater.from(context));
    }

    public View getView(){
        return bd.getRoot();
    }
}
