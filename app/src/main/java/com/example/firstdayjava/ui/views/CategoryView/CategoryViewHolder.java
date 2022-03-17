package com.example.firstdayjava.ui.views.CategoryView;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewUserBinding;
import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.ui.dialogs.UserInfoDialog.UserInfoDialog;
import com.example.firstdayjava.ui.dialogs.UserInfoDialog.UserInfoDialogListener;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    ViewUserBinding bd;


    CategoryViewListener listener;

    public CategoryViewHolder(@NonNull View itemView, CategoryViewListener listener) {
        super(itemView);
        bd = ViewUserBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    private void initEvent() {

    }

    public void bind() {

    }

}
