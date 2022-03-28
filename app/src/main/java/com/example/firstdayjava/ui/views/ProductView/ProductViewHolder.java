package com.example.firstdayjava.ui.views.ProductView;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.databinding.ViewProductBinding;
import com.example.firstdayjava.pojo.local.entities.Category;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ViewProductBinding bd;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        bd  = ViewProductBinding.bind(itemView);
    }

    public void bind(Category productCardData) {

    }
}
