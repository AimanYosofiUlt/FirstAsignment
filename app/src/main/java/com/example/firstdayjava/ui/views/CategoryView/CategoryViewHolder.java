package com.example.firstdayjava.ui.views.CategoryView;

import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewCategoryBinding;
import com.example.firstdayjava.pojo.local.entities.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    ViewCategoryBinding bd;
    CategoryViewListener listener;
    Category category;

    public CategoryViewHolder(@NonNull View itemView, CategoryViewListener listener) {
        super(itemView);
        bd = ViewCategoryBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    private void initEvent() {
        bd.circleCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnime();
            }

            private void startAnime() {
                listener.onClick(category);
                bd.circleCL.startAnimation(AnimationUtils.loadAnimation(
                        bd.getRoot().getContext(),
                        R.anim.bounce
                ));
            }
        });
    }

    public void bind(Category category) {
        this.category = category;
        bd.categoryNameTV.setText(category.getCategoryName());
        Glide.with(itemView.getContext())
                .load(category.getImageUrl())
                .circleCrop()
                .into(bd.image);
    }

}
