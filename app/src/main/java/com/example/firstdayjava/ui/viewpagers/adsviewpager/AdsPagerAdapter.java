package com.example.firstdayjava.ui.viewpagers.adsviewpager;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.remote.models.category.AdsSlider;

import java.util.ArrayList;
import java.util.List;

public class AdsPagerAdapter extends RecyclerView.Adapter<AddsPagerViewHolder> {
    List<AdsSlider> list = new ArrayList<>();

    public void setList(List<AdsSlider> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddsPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddsPagerViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ads,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AddsPagerViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
