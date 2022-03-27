package com.example.firstdayjava.ui.views.CategoryView;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewCategoryBinding;
import com.example.firstdayjava.databinding.ViewUserBinding;
import com.example.firstdayjava.pojo.dbs.models.Category;
import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.ui.dialogs.UserInfoDialog.UserInfoDialog;
import com.example.firstdayjava.ui.dialogs.UserInfoDialog.UserInfoDialogListener;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    ViewCategoryBinding bd;
//    CategoryViewListener listener;

    public CategoryViewHolder(@NonNull View itemView, CategoryViewListener listener) {
        super(itemView);
        bd = ViewCategoryBinding.bind(itemView);
//        this.listener = listener;
        initEvent();
    }

    private void initEvent() {

        init();
    }

    private void init() {
        bd.circleCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startAnime();
            }

            private void startAnime() {
                bd.circleCL.startAnimation(AnimationUtils.loadAnimation(
                        bd.getRoot().getContext(),
                        R.anim.bounce
                ));
            }
        });
    }

    public void bind(Category category) {
        bd.categoryNameTV.setText(category.getName());
//        bd.constraintLayout.setFilterTouchesWhenObscured();
    }

}
