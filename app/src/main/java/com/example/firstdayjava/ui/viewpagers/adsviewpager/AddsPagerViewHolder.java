package com.example.firstdayjava.ui.viewpagers.adsviewpager;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewAdsBinding;
import com.example.firstdayjava.pojo.remote.models.category.AdsSlider;

public class AddsPagerViewHolder extends RecyclerView.ViewHolder {
    ViewAdsBinding bd;
    public AddsPagerViewHolder(@NonNull View itemView) {
        super(itemView);
        bd = ViewAdsBinding.bind(itemView);
    }

    public void bind(AdsSlider slider){
        Glide.with(itemView.getContext())
                .load(slider.getImageUrl())
                .into(bd.image);
    }
}
