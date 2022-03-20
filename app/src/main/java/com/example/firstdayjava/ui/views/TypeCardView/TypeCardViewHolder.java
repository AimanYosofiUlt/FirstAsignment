package com.example.firstdayjava.ui.views.TypeCardView;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewTypeBinding;

public class TypeCardViewHolder extends RecyclerView.ViewHolder {

    ViewTypeBinding bd;

    TypeCardListener listener;

    public TypeCardViewHolder(@NonNull View itemView, TypeCardListener listener) {
        super(itemView);
        bd = ViewTypeBinding.bind(itemView);
        this.listener = listener;
    }

    TypeCardData type;

    public void bind(TypeCardData type) {
        this.type = type;
        bd.typeTV.setText(type.name);
        setCheck();
        bd.typeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type.isChecked = !type.isChecked;
                setCheck();
            }
        });
    }

    void setCheck() {
        if (type.isChecked) {
            bd.typeCard.setCardBackgroundColor(
                    ContextCompat.getColor(bd.getRoot().getContext(), R.color.blue)
            );
            bd.typeTV.setTextColor(Color.WHITE);
        } else {
            bd.typeCard.setCardBackgroundColor(Color.WHITE);
            bd.typeTV.setTextColor(Color.BLACK);
        }
    }

    interface TypeCardListener {
        void onCheckChange(TypeCardData type);
    }
}
