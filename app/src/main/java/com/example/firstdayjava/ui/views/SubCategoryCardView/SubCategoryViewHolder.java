package com.example.firstdayjava.ui.views.SubCategoryCardView;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewTypeBinding;

public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
    ViewTypeBinding bd;
    SubCategoryCardListener listener;

    public SubCategoryViewHolder(@NonNull View itemView, SubCategoryCardListener listener) {
        super(itemView);
        bd = ViewTypeBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public SubCategoryCard card;

    public void bind(SubCategoryCard card) {
        this.card = card;
        String title = card.getSubCategory().getName();
        bd.typeTV.setText(title);

        setCardCheck();
    }

    private void initEvent() {
        bd.typeCard.setOnClickListener(view -> {
            listener.onCheckChange(this);
        });
    }

    private void setCardCheck() {
        if (card.isChecked) {
            setChecked();
        } else {
            setNotChecked();
        }
    }

    public void setChecked() {
        card.isChecked = true;
        int srcBlueColor = ContextCompat.getColor(bd.getRoot().getContext(), R.color.blue);
        bd.typeCard.setCardBackgroundColor(srcBlueColor);
        bd.typeTV.setTextColor(Color.WHITE);
    }

    public void setNotChecked() {
        card.isChecked = false;
        bd.typeCard.setCardBackgroundColor(Color.WHITE);
        bd.typeTV.setTextColor(Color.BLACK);
    }


}
