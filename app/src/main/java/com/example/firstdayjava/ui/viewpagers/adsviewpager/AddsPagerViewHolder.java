package com.example.firstdayjava.ui.viewpagers.adsviewpager;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewAdsBinding;

public class AddsPagerViewHolder extends RecyclerView.ViewHolder {
    ViewAdsBinding bd;
    public AddsPagerViewHolder(@NonNull View itemView) {
        super(itemView);
        bd = ViewAdsBinding.bind(itemView);
    }

    public void bind(int drawable){
        bd.image.setImageResource(drawable);
    }
}
